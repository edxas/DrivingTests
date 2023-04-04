package com.example.learningPlatform.controllers;

import com.example.learningPlatform.model.Question;
import com.example.learningPlatform.services.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("questions", questions);
        return "questionList";
    }
}
