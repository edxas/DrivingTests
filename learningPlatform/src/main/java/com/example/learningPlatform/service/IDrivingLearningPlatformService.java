package com.example.learningPlatform.service;

import com.example.learningPlatform.model.Users;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;



public interface IDrivingLearningPlatformService {


    boolean newUser(Users users) throws NoSuchAlgorithmException;
    boolean authoriseUser(String email, String password);
    Users getAuthorisedUser();
}
