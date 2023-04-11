package com.example.learningPlatform.controller;

import com.example.learningPlatform.model.Role;
import com.example.learningPlatform.model.Salt;
import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.service.IDrivingLearningPlatformService;
import com.example.learningPlatform.service.impl.IDrivingLearningPlatformServiceImpl;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
public class UserController {
    @Autowired
    IDrivingLearningPlatformServiceImpl drivingLearningPlatformService;

    @GetMapping(value="/newUser")
    public String getAddUser(Model model, Users users){


        return "new-user";
    }

    @PostMapping(value="/newUser")
    public String setAddUser(ServletRequest request, Model model, @Valid Users users, BindingResult result ) throws NoSuchAlgorithmException {


        if(null != request.getParameter("reg")){

            if(!result.hasErrors()){
                if (drivingLearningPlatformService.newUser(users)) {

                    return "redirect:/home";
                }
                else {
                    model.addAttribute("error", "User with such e-mail or username already exist");
                }
            }
        }
        if(null != request.getParameter("log")){
            return "redirect:/login";
        }


        return "new-user";
    }
}
