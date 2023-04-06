package com.example.learningPlatform.services;

import com.example.learningPlatform.model.Question;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Data
@Slf4j
public class QuestionService {
    @Value("${data}")
    boolean useData;
    @Autowired
    QuestionDataService dataService;
    private ArrayList<Question> questions = new ArrayList<>();

    public ArrayList<Question> getQuestions(){
        LOG.info(String.valueOf(useData));
        if(useData){
            ArrayList<Question> list = new ArrayList<>();
            dataService.findAll().forEach(question -> list.add(question));
            LOG.info("size of current visitors listing: " + list.size());
            return  list;
        }
        return questions;
    }
    public void addQuestion(Question incoming) {
        LOG.info(String.valueOf(useData));
        if (useData) {
            LOG.info(
                    dataService.save(incoming)
                            .toString());
            return;
        }
        questions.add(incoming);
    }
}
