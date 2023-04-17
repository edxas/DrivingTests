package com.example.learningPlatform.service;

import com.example.learningPlatform.model.Questions;
import com.example.learningPlatform.model.Tests;
import com.example.learningPlatform.model.Users;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public interface IDrivingLearningPlatformService {


    boolean newUser(Users users) throws NoSuchAlgorithmException;
    boolean authoriseUser(String username, String password);
    Users getAuthorisedUser();
    void logOut();
    boolean editUser(Users users);
    Users getUserById(int id);
    boolean changePassword(String newPassword, String username, String password) throws NoSuchAlgorithmException;
    ArrayList<Questions> getTestQuestions (int id);
    int generateRandomTest ();
    void saveChosenAnswers(ArrayList<String> chosenAnswers, int id);
    Tests getTestById(int id);
    double calculateResult( ArrayList<Questions> questions,ArrayList<String> answers, ArrayList<Boolean> isCorect, int id);
}
