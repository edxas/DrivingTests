package com.example.learningPlatform.controller;


import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.service.IDrivingLearningPlatformService;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("login")
public class AuthoriseController {

    @Autowired
    IDrivingLearningPlatformService drivingLearningPlatformService;

    @GetMapping
    public  String getAuthorise( Users users){
        return "user-authorise";
    }

    @PostMapping
    public  String setAuthorise(Model model, ServletRequest request, Users users){

        if(null != request.getParameter("log")){
            if(drivingLearningPlatformService.authoriseUser(users.getUsername(), users.getPassword())){

                return "redirect:/home";
            }
        }
        if(null != request.getParameter("reg")){
            return "redirect:/newUser";
        }
        model.addAttribute("error", "Wrong username or password");

        return "user-authorise";
    }

}
