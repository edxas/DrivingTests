package com.example.learningPlatform.controllers;

import com.example.learningPlatform.model.Questions;
import com.example.learningPlatform.model.Topic;
import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.service.IDrivingLearningPlatformService;
import com.example.learningPlatform.services.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@Slf4j
public class QuestionController {
    @Autowired
    QuestionService service;
    @Autowired
    IDrivingLearningPlatformService drivingLearningPlatformService;
    @GetMapping("/seeQuestions")
    public String seeQuestions(Users user, Model model) throws IOException {

        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();


        if(authorisedUser.getRole()=="guest" || authorisedUser.getRole()=="user" ){
            return "redirect:/home";
        }

        LOG.info("Retrieving all questions");
        System.out.println("Retrieving all questions: the system out version");
        ArrayList<Questions> questions = service.getQuestions();
/*        String image = service.getQuestionBase64String(questions.get(1));
        model.addAttribute("image", image);*/
        //service.populateInitalUploadPhotos();//just for local testing
        model.addAttribute("question", questions);
        return "question-list";
    }
    @GetMapping("/addNewQuestion")
    public String createNewQuestion(Model model){
        model.addAttribute("question", new Questions());
        model.addAttribute("topics", Topic.values());
        return "question-add";
    }
    @GetMapping(value = "/editQuestion/{id}")
    public String updateForm(@PathVariable int id, Model model) {
        Questions question = service.getQuestion(id);
        if(question == null) return"redirect:/seeQuestions";
        String image = service.getQuestionBase64String(question);
        model.addAttribute("image", image);
        model.addAttribute("question", question);
        model.addAttribute("topics", Topic.values());
        model.addAttribute("answersnew", Arrays.asList(question.getAnswers()));
        model.addAttribute("correctnew", Arrays.asList(question.getCorrect_answers()));
        return "question-edit";
    }
/*    @GetMapping(value = "/photo")
    public String updatePhoto() throws IOException {
        service.populateInitalUploadPhotos();
        return"redirect:/seeQuestions";
    }*/
    @PostMapping("/addNewQuestion")
    public String createNewQuestions(Questions question, @RequestParam(name="image") MultipartFile file) {
        question.setQuestion_photo(service.inputImageToBytes(file));
        question.setAnswers(new String[]{service.convertToStringLine(question.getAnswers())});
        question.setCorrect_answers(new String[]{service.convertToStringLine(question.getCorrect_answers())});
        service.addQuestion(question);
        return"redirect:/seeQuestions";
    }
    @PostMapping("/editQuestion/{id}")
    public String updateQuestions(@PathVariable int id,Questions question,@RequestParam(name="image") MultipartFile file) {
        if(file.getSize() != 0) question.setQuestion_photo(service.inputImageToBytes(file));
        question.setAnswers(new String[]{service.convertToStringLine(question.getAnswers())});
        question.setCorrect_answers(new String[]{service.convertToStringLine(question.getCorrect_answers())});
        service.updateQuestion(question,id);
        return"redirect:/seeQuestions";
    }
    @RequestMapping(value="/deleteQuestion/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public String deleteQuestion(@PathVariable("id") int id) {
        service.deleteQuestionById(id);
        return"redirect:/seeQuestions";
    }
}
