package com.example.learningPlatform.controller;

import com.example.learningPlatform.model.Role;
import com.example.learningPlatform.model.Salt;
import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.service.IDrivingLearningPlatformService;
import com.example.learningPlatform.service.impl.IDrivingLearningPlatformServiceImpl;
import com.example.learningPlatform.tempClass.Passwords;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value="/user")
    public String getUser(Model model, Users user){
        user = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("user",user);


        return "user-profile";
    }

    @PostMapping(value="/user")
    public String setUser(ServletRequest request, Model model,Users user , RedirectAttributes redirAttr) throws NoSuchAlgorithmException {

        user = drivingLearningPlatformService.getAuthorisedUser();


        if(null != request.getParameter("logOut")){
            drivingLearningPlatformService.logOut();

            return "redirect:/home";
        }
        if(null != request.getParameter("edit")){
            redirAttr.addFlashAttribute("user",user);

            return "redirect:/editUser/"+user.getId() +"/";
        }
        if(null != request.getParameter("password")){

            return "redirect:/editUser/password";
        }


        return "user-profile";
    }

    @GetMapping(value="/editUser/{id}/")
    public String getEditUser(Model model,@PathVariable(name = "id") int id, Users authorisedUser, Users users ){
        authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("authorisedUser",authorisedUser);
        users = drivingLearningPlatformService.getUserById(id);
        users.setPassword("Testt66$");
        model.addAttribute("users",users);



        return "edit-user";
    }

    @PostMapping(value="/editUser/{id}/")
    public String setEditUser(ServletRequest request,Users authorisedUser, @PathVariable(name = "id") int id,Model model, @Valid Users users, BindingResult result ) throws NoSuchAlgorithmException {

        System.out.println(result.getAllErrors());
        if(null != request.getParameter("save")){

            if(!result.hasErrors()){
                if (drivingLearningPlatformService.editUser(users)) {

                    return "redirect:/user";
                }

            }


        }

        if(null != request.getParameter("logOut")){
            drivingLearningPlatformService.logOut();

            return "redirect:/home";
        }
        authorisedUser = drivingLearningPlatformService.getAuthorisedUser();

        model.addAttribute("authorisedUser",authorisedUser);

        model.addAttribute("users",users);
        return "edit-user";
    }

    @GetMapping(value="/editUser/password")
    public String getEditPassword(Model model, Passwords passwords ){
        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());





        return "edit-password";
    }

    @PostMapping(value="/editUser/password")
    public String setEditPassword(ServletRequest request, Model model, @Valid Passwords passwords, BindingResult result ) throws NoSuchAlgorithmException {
        System.out.println("Error post mapping -false");
        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();

        if(null != request.getParameter("saveButton")){
            System.out.println(result.getAllErrors());
            if(!result.hasErrors()){
                if(drivingLearningPlatformService.changePassword(passwords.getNewPassword(),authorisedUser.getUsername(),passwords.getOldPassword())){


                    return "redirect:/user";
                }
                else {
                    System.out.println(authorisedUser.getUsername());
                    model.addAttribute("error", "Wrong old password");
                }

            }


        }

        if(null != request.getParameter("logOut")){
            drivingLearningPlatformService.logOut();

            return "redirect:/home";
        }
        authorisedUser = drivingLearningPlatformService.getAuthorisedUser();

        model.addAttribute("role",authorisedUser.getRole());


        return "edit-password";
    }


}
