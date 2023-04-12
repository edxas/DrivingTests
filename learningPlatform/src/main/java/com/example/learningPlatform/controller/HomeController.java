package com.example.learningPlatform.controller;


import com.example.learningPlatform.model.Topic;
import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.service.IDrivingLearningPlatformService;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"","/","/home"})
public class HomeController {
    @Autowired
    IDrivingLearningPlatformService drivingLearningPlatformService;

    @GetMapping
    public  String getHome(Users user, Model model){
        user = drivingLearningPlatformService.getAuthorisedUser();

        model.addAttribute("user",user);
        return "home";
    }

    @PostMapping
    public  String setHome(Users user,ServletRequest request, Model model){


        if(null != request.getParameter("signUP")){
            return "redirect:/newUser";
        }
        if(null != request.getParameter("logIN")){
            return "redirect:/login";
        }
        if(null != request.getParameter("logOut")){
            drivingLearningPlatformService.logOut();
            user = drivingLearningPlatformService.getAuthorisedUser();
            model.addAttribute("user",user);
            model.addAttribute("topics", Topic.values());
            return "home";
        }

        return "home";
    }
}
