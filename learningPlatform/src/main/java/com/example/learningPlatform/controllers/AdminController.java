package com.example.learningPlatform.controllers;


import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.services.UpdateValidation;
import com.example.learningPlatform.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static com.example.learningPlatform.model.Role.user;


@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView("admin");
        List<Users> users = userService.getAllUsers();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/editUsers/{id}")
    public ModelAndView userEditPage(@PathVariable("id") Long id ) {
        ModelAndView modelAndView = new ModelAndView("userEdit");
        Users user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @PostMapping("/editUsers/{id}")
    public ModelAndView userUpdate(@ModelAttribute("user") @Validated(UpdateValidation.class) Users user, BindingResult bindingResult) {
        Optional<Users> optionalUser = Optional.ofNullable(userService.getUserById((long) user.getId()));
        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();

            existingUser.setName(user.getName());
            existingUser.setSurname(user.getSurname());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());

            user.setUsername(existingUser.getUsername());
            user.setPassword(existingUser.getPassword());
            user.setHashPassword(existingUser.getHashPassword());
            user.setSalt(existingUser.getSalt());

                userService.updateUser(user);
                ModelAndView modelAndView = new ModelAndView("redirect:/admin");
                modelAndView.addObject("user", user);
                return modelAndView;
            } else {

                ModelAndView modelAndView = new ModelAndView("userEdit");
                modelAndView.addObject("user", user);
                modelAndView.addObject("error", "User not found");
                return modelAndView;
            }
    }

    @GetMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        String salt = userService.deleteUser(id);
        if (salt != null) {
            return new ResponseEntity<>("User with ID " + id + " deleted successfully. Salt: " + salt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }


}