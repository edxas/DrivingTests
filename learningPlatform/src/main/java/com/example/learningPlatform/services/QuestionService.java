package com.example.learningPlatform.services;

import com.example.learningPlatform.model.Question;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
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
    public Question getQuestion(int id){
        //System.out.println("Inside getQuestion");
        Question question1 = dataService.findById(id);
        //System.out.println(question1);
        return  question1;
    }
    public String getQuestionBase64String(Question question){
        String imageString = "";
        byte[] image = question.getQuestion_photo();
        if(image.length > 0) imageString = Base64.encodeBase64String(image);
        return  imageString;
    }
    public void addQuestion(Question incoming) {
        LOG.info(String.valueOf(useData));
        if (useData) {
            dataService.save(incoming);
            //Creating a copy of a question and replacing BLOB data, so it does not flood the console (replacing only notEmpty pictures)
            Question questionSmall = new Question(incoming.getId(),incoming.getTopic(),incoming.getQuestion(),incoming.getAnswers(), incoming.getCorrect_answers(),incoming.getHint(), incoming.getQuestion_photo());
            if(questionSmall.getQuestion_photo().length > 0) questionSmall.setQuestion_photo("NotEmpty".getBytes());
            LOG.info(questionSmall.toString());
            return;
        }
        questions.add(incoming);
    }
    public void deleteQuestionById(int id) {
        //System.out.println("Inside service deleteQuestionById method");
        Question test = dataService.findById(id);
        //System.out.println(test);
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
