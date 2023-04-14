package com.example.learningPlatform.service;

import com.example.learningPlatform.model.Users;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;



public interface IDrivingLearningPlatformService {


    boolean newUser(Users users) throws NoSuchAlgorithmException;
    boolean authoriseUser(String username, String password);
    Users getAuthorisedUser();
    void logOut();
    boolean editUser(Users users);
    Users getUserById(int id);
    boolean changePassword(String newPassword, String username, String password) throws NoSuchAlgorithmException;
}
