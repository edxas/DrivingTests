package com.example.learningPlatform.controllers;

import com.example.learningPlatform.model.Question;
import com.example.learningPlatform.services.QuestionService;
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
import java.util.ArrayList;

@Controller
@Slf4j
public class QuestionController {
    @Autowired
    QuestionService service;
    @GetMapping("/seeQuestions")
    public String seeQuestions(Model model){
        LOG.info("Retrieving all questions");
        System.out.println("Retrieving all questions: the system out version");
        ArrayList<Question> questions = service.getQuestions();
        model.addAttribute("question", questions);
        return "questionList";
    }
    @GetMapping("/addNewQuestion")
    public String createNewQuestion(Model model){
        model.addAttribute("question", new Question());
        //service.addQuestion(question);
        return "questionAdd";
    }
    @PostMapping(value = "/addNewQuestion")
    public String createNewQuestion(ServletRequest request, RedirectAttributes redirAttr, @Valid Question question, BindingResult result ) throws NoSuchAlgorithmException {
        System.out.println("Inside post 1");
        if(null != request.getParameter("add")){
            System.out.println("Inside post 2");
            if(!result.hasErrors()){
                System.out.println("Inside post 3");
                //if(d)
                //service.addQuestion(question);
                return "questionList";
            }
        }
        System.out.println("Inside post 4");
        //model.addAttribute("question",question);
        //service.addQuestion(question);
        return "questionList";
    }
    @PostMapping(value = "/process_question")
    public String createNewQuestions(Question question){
        //model.addAttribute("question",question);

        service.addQuestion(question);
        return"redirect:/seeQuestions";
    }
/*    @GetMapping("/addNewQuestion")
    public String createQuestion(){
        ArrayList<Question> questions = service.getQuestions();
        Question question = new Question(questions.size(),Topic.signs,"BlaBla","Answers","CorrectAnswers");
        service.addQuestion(question);
        return "questionList";
    }*/
}
