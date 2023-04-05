package com.example.learningPlatform.controller;


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
    public  String getHome( Model model){
        return "home";
    }

    @PostMapping
    public  String setHome(ServletRequest request){
        if(null != request.getParameter("logUP")){
            return "redirect:/newUser";
        }
        if(null != request.getParameter("logIN")){
            return "redirect:/login";
        }

        return "home";
    }
}
