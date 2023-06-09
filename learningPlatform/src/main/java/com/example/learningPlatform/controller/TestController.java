package com.example.learningPlatform.controller;

import com.example.learningPlatform.model.Questions;
import com.example.learningPlatform.model.Tests;
import com.example.learningPlatform.model.Topic;
import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.service.impl.IDrivingLearningPlatformServiceImpl;
import com.example.learningPlatform.services.QuestionService;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;

@Controller
public class TestController {
    @Autowired
    IDrivingLearningPlatformServiceImpl drivingLearningPlatformService;
    @Autowired
    QuestionService questionService;

    ArrayList<Questions> questions= new ArrayList<>();
    ArrayList<String> userAnswers= new ArrayList<>();
    Questions question1 = null;
    int testSize = 0;

    @GetMapping(value="/test/question/{id}")
    public String getQuestionInTest(Model model, @PathVariable(name = "id") int id){

        questions= drivingLearningPlatformService.getTestQuestions(id);
        userAnswers.removeAll(userAnswers);

        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        testSize = questions.size();
        if(authorisedUser.getRole()=="guest" && id !=1){
            return "redirect:/home";
        }

        if(questions != null){
            question1 = questions.remove(0);
        }
        else {
            return "redirect:/test/result";
        }
        model.addAttribute("role",authorisedUser.getRole());
        String answersString = question1.getAnswers()[0];
        if(answersString.contains("[")){
            answersString = answersString.substring(1,answersString.length()-1);
        }
        String[] temp=answersString.split("; ");
        temp=answersString.split(";");
        model.addAttribute("answers",temp);
        model.addAttribute("question1", question1);
        model.addAttribute("size",testSize);
        model.addAttribute("actualSize", 0);
        String image = questionService.getQuestionBase64String(question1);
        model.addAttribute("image", image);
        return "question-template";
    }


    @PostMapping(value="/test/question/{id}")
    public String setEditPassword(ServletRequest request, @PathVariable(name = "id") int id,ArrayList<String> answers, Model model) {
        ArrayList<Boolean> chosenAnswers = new ArrayList<>();

        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();

        int actualSize = testSize-questions.size();
        String answersString = question1.getAnswers()[0];
        if(answersString.contains("[")){
            answersString = answersString.substring(1,answersString.length()-1);
        }
        String[] temp=answersString.split("; ");
        temp=answersString.split(";");
        for (int i=0; i<temp.length; i++){
            chosenAnswers.add(request.getParameter(""+i) != null);
        }

        authorisedUser = drivingLearningPlatformService.getAuthorisedUser();


        if(null != request.getParameter("next")){
            if (chosenAnswers.contains(true)){
                String temp2 = "";
                for (int i =0 ; i<temp.length; i++) {
                    if(chosenAnswers.get(i) == true){


                        temp2 = temp2+ temp[i]+"|";

                    }


                }
                userAnswers.add(temp2);

                if(questions.size()>0){
                    question1 = questions.remove(0);
                }
                else {
                    drivingLearningPlatformService.saveChosenAnswers(userAnswers,id);


                    return "redirect:/test/result/"+id;
                }
            }
            else {
                model.addAttribute("error", "You need to chose answer");
            }
        }

        model.addAttribute("role",authorisedUser.getRole());

        model.addAttribute("size",testSize);
        model.addAttribute("actualSize", actualSize);
        answersString = question1.getAnswers()[0];
        if(answersString.contains("[")){
            answersString = answersString.substring(1,answersString.length()-1);
        }
        temp = answersString.split("; ");
        temp = answersString.split(";");
        model.addAttribute("answers",temp);
        model.addAttribute("question1", question1);
        //System.out.println(question1);
        String image = questionService.getQuestionBase64String(question1);
        model.addAttribute("image", image);

        return "question-template";
    }

    @GetMapping(value="/test/result/{id}")
    public String getResult(Model model, @PathVariable(name = "id") int id) {

        ArrayList<Questions >questionsS = drivingLearningPlatformService.getTestQuestions(id);
        Tests test1 = drivingLearningPlatformService.getTestById(id);

        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());
        double score = 0;
        ArrayList<Boolean> isCorect = new ArrayList<>();

        score=drivingLearningPlatformService.calculateResult(questionsS,test1.getUser_chosen_answer_list(), isCorect, id);
        model.addAttribute("questions", questionsS);
        System.out.println(score);
        String chosenAnswersString = test1.getUser_chosen_answer_list().get(0);
        if(chosenAnswersString.contains("[")){
            chosenAnswersString = chosenAnswersString.substring(1,chosenAnswersString.length()-1);
        }
        String[] answersChosen=chosenAnswersString.split("; ");
        answersChosen=chosenAnswersString.split(";");
        model.addAttribute("userAnswers",answersChosen);
        model.addAttribute("isCorrect",isCorect);
        model.addAttribute("score", score);


        return "result";
    }
    @GetMapping(value="allTests")
    public String getAllTests(Model model) {

        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());





        return "result";
    }

    @GetMapping(value="/tests/signs")
    public String getSignTest(Model model){
        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());
        if(authorisedUser.getRole()=="guest"){
            return "redirect:/home";
        }


        return "signs-test";
    }
    @PostMapping(value="/tests/signs")
    public String setSignTest(ServletRequest request,Model model){
        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());
        if(null != request.getParameter("start")) {
            return "redirect:/test/question/"+drivingLearningPlatformService.generateSignTest();
        }


        return "signs-test";
    }

    @GetMapping(value="/tests/driving_priority")
    public String getPriorityTest(Model model){
        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());
        if(authorisedUser.getRole()=="guest"){
            return "redirect:/home";
        }


        return "priority-test";
    }
    @PostMapping(value="/tests/driving_priority")
    public String setPriorityTest(ServletRequest request,Model model){
        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());
        if(null != request.getParameter("start")) {
            return "redirect:/test/question/"+drivingLearningPlatformService.generatePriorityTest();
        }


        return "priority-test";
    }

    @GetMapping(value="/tests/random")
    public String getRandomTest(Model model){
        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());
        if(authorisedUser.getRole()=="guest"){
            return "redirect:/home";
        }


        return "random-test";
    }
    @PostMapping(value="/tests/random")
    public String setRandomTest(ServletRequest request,Model model){
        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());
        if(null != request.getParameter("start")) {
            return "redirect:/test/question/"+drivingLearningPlatformService.generateRandomTest();
        }


        return "random-test";
    }

    @GetMapping(value="/tests/allTests")
    public String getAllTest(Model model){
        Users authorisedUser = drivingLearningPlatformService.getAuthorisedUser();
        model.addAttribute("role",authorisedUser.getRole());
        if(authorisedUser.getRole()=="guest"){
            return "redirect:/home";
        }

        ArrayList<Tests> tests = drivingLearningPlatformService.getTestByUserId(authorisedUser.getId());
        if (tests.size()>10){
            tests = drivingLearningPlatformService.getTenTestByUserId(authorisedUser.getId());
        }
        model.addAttribute("tests",tests);

        double avgScore = 0.0;
        double avgScoreSigns = 0.0;
        double avgScorePriority = 0.0;
        double avgScoreRandom = 0.0;
        avgScore=drivingLearningPlatformService.calculateAvgScore(authorisedUser.getId());
        avgScoreRandom = drivingLearningPlatformService.calculateAvgScoreRandom(authorisedUser.getId());
        avgScorePriority = drivingLearningPlatformService.calculateAvgScorePriority(authorisedUser.getId());
        avgScoreSigns = drivingLearningPlatformService.calculateAvgScoreSigns(authorisedUser.getId());
        model.addAttribute("avgScore",avgScore);
        model.addAttribute("avgScoreS",avgScoreSigns);
        model.addAttribute("avgScoreP",avgScorePriority);
        model.addAttribute("avgScoreR",avgScoreRandom);
        double diffRandom = drivingLearningPlatformService.calculateDifferenceBetweenTwo(authorisedUser.getId(), Topic.random.name());
        model.addAttribute("diffRandom", diffRandom);
        double diffSigns = drivingLearningPlatformService.calculateDifferenceBetweenTwo(authorisedUser.getId(), Topic.signs.name());
        model.addAttribute("diffSigns", diffSigns);
        double diffPr = drivingLearningPlatformService.calculateDifferenceBetweenTwo(authorisedUser.getId(), Topic.driving_priority.name());
        model.addAttribute("diffPr", diffPr);



        return "all-tests";
    }

}
