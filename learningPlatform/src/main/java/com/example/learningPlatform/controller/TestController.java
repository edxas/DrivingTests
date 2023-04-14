package com.example.learningPlatform.controller;

import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.service.impl.IDrivingLearningPlatformServiceImpl;
import com.example.learningPlatform.tempClass.Passwords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TestController {
    @Autowired
    IDrivingLearningPlatformServiceImpl drivingLearningPlatformService;

    @GetMapping(value="/test/question")
    public String getQuestionInTest(Model model){
        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());




        return "question-template";
    }
}
