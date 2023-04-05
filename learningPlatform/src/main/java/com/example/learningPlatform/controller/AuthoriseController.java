package com.example.learningPlatform.controller;


import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.service.IDrivingLearningPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class AuthoriseController {

    @Autowired
    IDrivingLearningPlatformService drivingLearningPlatformService;

    @GetMapping
    public  String getAuthorise(Users users, Model model){
        return "user-authorise";
    }

    @PostMapping
    public  String setAuthorise(Users users, BindingResult result){
        if(drivingLearningPlatformService.authoriseUser(users.getUsername(), users.getPassword())){
            long id = drivingLearningPlatformService.getAuthorisedUser().getId();
            return "redirect:/home";
        }

        return "user-authorise";
    }

}
