package com.example.learningPlatform.services;

import com.example.learningPlatform.model.Question;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Data
@Slf4j
@Transactional
public class QuestionService {
    @Value("${data}")
    boolean useData;
    @Autowired
    QuestionDataService dataService;
    private ArrayList<Question> questions = new ArrayList<>();
    private Question question = new Question();

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
    public void getQuestion(int id){
        System.out.println("Inside getQuestion");
        Question test = dataService.findById(id);
        System.out.println(test);

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
    public void deleteQuestionById(int id) {
        System.out.println("Inside service deleteQuestionById method");
        Question test = dataService.findById(id);
        System.out.println(test);
        dataService.deleteById(id);
    }
    public String convertToStringLine(String[] answers){
        String line = "";
        LOG.info(String.valueOf(useData));
        if (useData) {
            for(int i=0; i<answers.length; i++)
            {
                line+=answers[i] +";";
            }
            return line;
        }
        return line;
    }
}
