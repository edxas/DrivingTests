package com.example.learningPlatform.controllers;

import com.example.learningPlatform.model.Question;
import com.example.learningPlatform.model.Topic;
import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.services.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("topics", Topic.values());
        return "questionAdd";
    }
    @PostMapping("/addNewQuestion")
    public String createNewQuestions(Question question){
        System.out.println(question);
        service.addQuestion(question);
        return"redirect:/seeQuestions";
    }
    //@DeleteMapping("/deleteQuestion/{id}")
    @RequestMapping(value="/deleteQuestion/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
    public String deleteQuestion(@PathVariable("id") int id) {
        service.deleteQuestionById(id);
        return"redirect:/seeQuestions";
    }
}
