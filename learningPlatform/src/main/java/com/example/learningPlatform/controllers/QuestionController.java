package com.example.learningPlatform.controllers;

import com.example.learningPlatform.model.Question;
import com.example.learningPlatform.model.Topic;
import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.services.QuestionService;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;



import java.io.*;
import java.util.ArrayList;

@Controller
@Slf4j
public class QuestionController {
    @Autowired
    QuestionService service;
    @GetMapping("/seeQuestions")
    public String seeQuestions(Model model){
        LOG.info("Retrieving all questions");
        System.out.println("Retrieving all questions: the system out version");
        ArrayList<Question> questions = service.getQuestions();
/*        String image = service.getQuestionBase64String(service.getQuestion(questions.size()));
        model.addAttribute("image", image);*/
        model.addAttribute("question", questions);
        return "questionList";
    }
    @GetMapping("/addNewQuestion")
    public String createNewQuestion(Model model){
        model.addAttribute("question", new Question());
        model.addAttribute("topics", Topic.values());
        return "questionAdd";
    }
    @PostMapping("/addNewQuestion")
    public String createNewQuestions(Question question,@RequestParam(name="image") MultipartFile file) {

        System.out.println("file");
        System.out.println("File Name :" + file.getName() );
        System.out.println("File Size :" + file.getSize() );
        System.out.println("File Size :" + file.getContentType() );
        byte[] filecontent = null;
        try {
            InputStream inputStream = file.getInputStream();
            if(inputStream == null)
                System.out.println("file InputStream is null");
            filecontent = IOUtils.toByteArray(inputStream);
            question.setQuestion_photo(filecontent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        service.addQuestion(question);
        return"redirect:/seeQuestions";
    }
    @RequestMapping(value="/deleteQuestion/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public String deleteQuestion(@PathVariable("id") int id) {
        service.deleteQuestionById(id);
        return"redirect:/seeQuestions";
    }
}
