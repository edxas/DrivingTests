package com.example.learningPlatform.service;

import com.example.learningPlatform.model.Users;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service

public interface IDrivingLearningPlatformService {


    public boolean newUser(Users users) throws NoSuchAlgorithmException;
    public boolean authoriseUser(String email, String password);
    public Users getAuthorisedUser();
}
