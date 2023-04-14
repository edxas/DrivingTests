package com.example.learningPlatform.controllers;

import com.example.learningPlatform.model.Question;
import com.example.learningPlatform.model.Topic;
import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.services.QuestionService;
import com.example.learningPlatform.tempClass.Passwords;
import lombok.extern.slf4j.Slf4j;
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
    public String seeQuestions(Model model) throws IOException {
        LOG.info("Retrieving all questions");
        System.out.println("Retrieving all questions: the system out version");
        ArrayList<Question> questions = service.getQuestions();
/*        String image = service.getQuestionBase64String(questions.get(1));
        model.addAttribute("image", image);*/
        service.populateInitalUploadPhotos();//just for local testing
        model.addAttribute("question", questions);
        return "questionList";
    }
    @GetMapping("/addNewQuestion")
    public String createNewQuestion(Model model){
        model.addAttribute("question", new Question());
        model.addAttribute("topics", Topic.values());
        return "questionAdd";
    }
    @GetMapping(value = "/editQuestion/{id}")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("question", service.getQuestion(id));
        model.addAttribute("topics", Topic.values());
        return "questionAdd";
    }
/*    @GetMapping(value = "/photo")
    public String updatePhoto() throws IOException {
        service.populateInitalUploadPhotos();
        return"redirect:/seeQuestions";
    }*/
    @PostMapping("/addNewQuestion")
    public String createNewQuestions(Question question,@RequestParam(name="image") MultipartFile file) {
        question.setQuestion_photo(service.inputImageToBytes(file));
        service.addQuestion(question);
        return"redirect:/seeQuestions";
    }
    @RequestMapping(value="/deleteQuestion/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public String deleteQuestion(@PathVariable("id") int id) {
        service.deleteQuestionById(id);
        return"redirect:/seeQuestions";
    }
}
