package com.example.learningPlatform.service.impl;

import com.example.learningPlatform.model.*;
import com.example.learningPlatform.repo.ISaltRepo;
import com.example.learningPlatform.repo.ITestRepo;
import com.example.learningPlatform.repo.IUserRepo;
import com.example.learningPlatform.service.IDrivingLearningPlatformService;
import com.example.learningPlatform.services.QuestionDataService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

import static com.example.learningPlatform.model.SHA512Hasher.*;

@Service
@Data
@Slf4j
public class IDrivingLearningPlatformServiceImpl implements IDrivingLearningPlatformService {


    @Autowired
    IUserRepo userRepo;
    @Autowired
    ISaltRepo saltRepo;
    @Autowired
    ITestRepo testRepo;
    @Autowired
    QuestionDataService questionDataService;

    public Users usersStatic = null;



    @Override
    public boolean newUser(Users user) throws NoSuchAlgorithmException {
        Users users1 = userRepo.findByEmail(user.getEmail());
        Users users2 = userRepo.findByUsername(user.getUsername());
        if(users1 ==null && users2 == null ){
//
            Salt salt = new Salt(getSalt());
            String password1 = getSecurePassword(user.getPassword(), salt.getSalt());
            LOG.info(
                    userRepo.save(new Users(user.getName(), user.getSurname(), Role.user.name(), user.getEmail(), user.getUsername(),password1, user.getPassword(),salt))
                            .toString());
            return true;
        }
        return false;
    }

    @Override
    public boolean authoriseUser(String username, String password) {
        Users users1 = userRepo.findByUsername(username);


        if(users1 !=null){


            if (checkPassword(users1.getHashPassword(),password, users1.getSalt().getSalt())){
                usersStatic = users1;
                return true;
            }

        }
        return false;
    }

    @Override
    public Users getAuthorisedUser() {
        if (usersStatic != null){
            Users users1 = userRepo.findByUsername(usersStatic.getUsername());
            return users1;
        }

        return new Users("test","test",Role.guest.name(), "tesr@gmail.com","test","test","Test5$66",new Salt());
    }



    @Override
    public void logOut() {
        usersStatic = null;
    }

    @Override
    public boolean editUser(Users user) {
        Users users1 = userRepo.findByUsername(user.getUsername());

        if(users1 !=null  ){


            users1.setPassword("Testt66$");
            users1.setName(user.getName());
            users1.setSurname(user.getSurname());
            users1.setEmail(user.getEmail());
            userRepo.save(users1);
            return true;
        }
        return false;
    }

    @Override
    public Users getUserById(int id) {
        return userRepo.findById(id).get();
    }

    @Override
    public boolean changePassword(String newPassword, String username, String password) throws NoSuchAlgorithmException {


        if(authoriseUser(username,password)){
            Users users1 = userRepo.findByUsername(username);
            Salt salt = new Salt(getSalt());
            String password1 = getSecurePassword(newPassword, salt.getSalt());
            users1.setPassword(newPassword);
            users1.setSalt(salt);
            users1.setHashPassword(password1);
            userRepo.save(users1);

            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Questions> getTestQuestions(int id) {

        return questionDataService.findAllByTestslistId(id);

    }

    @Override
    public int generateRandomTest() {
        ArrayList<Questions>  questions= questionDataService.findAll();
        ArrayList<Questions> testQuestions = new ArrayList<>();
        for (int i =0; i<5; i++){
            testQuestions.add(questions.remove((int)Math.floor(Math.random() *(questions.size()) )));

        }

        testRepo.save(new Tests(Topic.random.name(),testQuestions,getAuthorisedUser()));

        return testRepo.findTopByOrderByIdDesc().getId();
    }

    @Override
    public void saveChosenAnswers(ArrayList<String> chosenAnswers, int id) {
        Tests test1 = testRepo.findById(id).get();
        String temp = "";
        for (String chosenAnswer : chosenAnswers) {
            temp = temp +chosenAnswer+";";
        }
        ArrayList<String> choses = new ArrayList<>();
        choses.add(temp);
        test1.setUser_chosen_answer_list(choses);
        testRepo.save(test1);


    }

    @Override
    public Tests getTestById(int id) {
        return testRepo.findById(id).get();
    }

    @Override
    public double calculateResult(ArrayList<Questions> questions, ArrayList<String> answers, ArrayList<Boolean> isCorect, int id) {
        double score = 0;
        String chosenAnswersString = answers.get(0);
        if(chosenAnswersString.contains("[")){
            chosenAnswersString = chosenAnswersString.substring(1,chosenAnswersString.length()-1);
        }

        String[] answersChosen=chosenAnswersString.split("; ");
        answersChosen=chosenAnswersString.split(";");

        for (int i=0; i<questions.size(); i++) {


            String correctAnswersString = questions.get(i).getCorrect_answers()[0];
            if(correctAnswersString.contains("[")){
                correctAnswersString = correctAnswersString.substring(1,correctAnswersString.length()-1);
            }
            String[] correctAnswers= null;
            if(correctAnswersString.contains(";")){
                correctAnswers=correctAnswersString.split("; ");
                correctAnswers=correctAnswersString.split(";");
            }
            else {
                correctAnswers= new String[]{correctAnswersString};
            }
            String[] answersChosen1= null;

            if(answersChosen[i].contains("|")){
                answersChosen1=answersChosen[i].split("\\|");
            }
            else {
                answersChosen1= new String[]{answersChosen[i]};
            }
            ArrayList<Boolean> tempIsCorrect = new ArrayList<>();
            for (String ss : answersChosen1) {

                Boolean isEqual = false;
                for (String s : correctAnswers) {

                    if(ss.equals(s)==true){
                        isEqual = true;
                        break;
                    }

                }
                tempIsCorrect.add(isEqual);

            }
            if (tempIsCorrect.contains(false)){
                isCorect.add(false);
            }
            else {
                isCorect.add(true);
                score +=1;

            }

        }

        score = score/questions.size()*10;
        score = Math.round(score * 100.0) / 100.0;

        Tests test1 = testRepo.findById(id).get();
        test1.setScore(score);
        testRepo.save(test1);
        return  score;

    }

    @Override
    public int generateSignTest() {
        ArrayList<Questions>  questions= questionDataService.findAllByTopic(Topic.signs.name());
        ArrayList<Questions> testQuestions = new ArrayList<>();
        for (int i =0; i<5; i++){
            testQuestions.add(questions.remove((int)Math.floor(Math.random() *(questions.size()) )));

        }

        testRepo.save(new Tests(Topic.signs.name(), testQuestions,getAuthorisedUser()));

        return testRepo.findTopByOrderByIdDesc().getId();
    }

    @Override
    public int generatePriorityTest() {
        ArrayList<Questions>  questions= questionDataService.findAllByTopic(Topic.driving_priority.name());
        ArrayList<Questions> testQuestions = new ArrayList<>();
        for (int i =0; i<5; i++){
            testQuestions.add(questions.remove((int)Math.floor(Math.random() *(questions.size()) )));

        }

        testRepo.save(new Tests(Topic.driving_priority.name(), testQuestions,getAuthorisedUser()));

        return testRepo.findTopByOrderByIdDesc().getId();
    }

    @Override
    public ArrayList<Tests> getTestByUserId(int id) {

        return testRepo.findAllByUseridId(id);
    }

    @Override
    public double calculateAvgScore(int id) {
        ArrayList<Tests> tests= testRepo.findAllByUseridId(id);
        ArrayList<Double> scores = new ArrayList<>();
        for (Tests test : tests) {
            scores.add(test.getScore());

        }
        double avgScore = 0;
        for (int i = 0; i < scores.size(); i++) {
            avgScore = avgScore+ scores.get(i);

        }
        if(avgScore != 0){
            avgScore = avgScore/scores.size();
            avgScore= Math.round(avgScore * 100.0) / 100.0;

            return avgScore;
        }

        return 0;
    }

    @Override
    public double calculateAvgScoreRandom(int id) {
        ArrayList<Tests> tests= testRepo.findAllByUseridIdAndTopic(id,Topic.random.name());
        ArrayList<Double> scores = new ArrayList<>();
        for (Tests test : tests) {
            scores.add(test.getScore());

        }
        double avgScore = 0;
        for (int i = 0; i < scores.size(); i++) {
            avgScore = avgScore+ scores.get(i);

        }
        if(avgScore != 0){
            avgScore = avgScore/scores.size();
            avgScore= Math.round(avgScore * 100.0) / 100.0;

            return avgScore;
        }

        return 0;
    }
    @Override
    public double calculateAvgScoreSigns(int id) {
        ArrayList<Tests> tests= testRepo.findAllByUseridIdAndTopic(id,Topic.signs.name());
        ArrayList<Double> scores = new ArrayList<>();
        for (Tests test : tests) {
            scores.add(test.getScore());

        }
        double avgScore = 0;
        for (int i = 0; i < scores.size(); i++) {
            avgScore = avgScore+ scores.get(i);

        }
        if(avgScore != 0){
            avgScore = avgScore/scores.size();
            avgScore= Math.round(avgScore * 100.0) / 100.0;

            return avgScore;
        }

        return 0;
    }
    @Override
    public double calculateAvgScorePriority(int id) {
        ArrayList<Tests> tests= testRepo.findAllByUseridIdAndTopic(id,Topic.driving_priority.name());
        ArrayList<Double> scores = new ArrayList<>();
        for (Tests test : tests) {
            scores.add(test.getScore());

        }
        double avgScore = 0;
        for (int i = 0; i < scores.size(); i++) {
            avgScore = avgScore+ scores.get(i);

        }
        if(avgScore != 0){
            avgScore = avgScore/scores.size();
            avgScore= Math.round(avgScore * 100.0) / 100.0;

            return avgScore;
        }

        return 0;
    }


    @Override
    public double calculateDifferenceBetweenTwo(int id, String topic) {
        ArrayList<Tests> tests= testRepo.findTop2ByUseridIdAndTopicOrderByIdDesc(id,topic);
        if(tests.size()>=2){
            double score1= tests.get(0).getScore();
            double score2= tests.get(1).getScore();

            return score1-score2;
        }
        return 0;
    }

    @Override
    public ArrayList<Tests> getTenTestByUserId(int id) {
        return testRepo.findTop10ByUseridIdOrderByIdDesc(id);
    }
}
