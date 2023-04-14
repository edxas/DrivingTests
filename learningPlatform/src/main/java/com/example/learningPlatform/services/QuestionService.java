package com.example.learningPlatform.services;

import com.example.learningPlatform.model.Question;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
    private boolean loadCheck = true;

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
        LOG.info(String.valueOf(useData));
        if(useData){
            question = dataService.findById(id);
            if(question != null) LOG.info("Got question: " + removeBlobForLogging(question).toString());
            else LOG.info("Question with id: "+id+" Not found");
            return question;
        }
        return question;
    }
    public String getQuestionBase64String(Question question){
        LOG.info(String.valueOf(useData));
        String imageString = "";
        if(useData){
            byte[] image = question.getQuestion_photo();
            if(image.length > 0){
                imageString = Base64.encodeBase64String(image);
                LOG.info("The question has a picture");
            }else LOG.info("The question does not have a picture");
            return  imageString;
        }
        return imageString;
    }
    public void addQuestion(Question incoming) {
        LOG.info(String.valueOf(useData));
        if (useData) {
            dataService.save(incoming);
            LOG.info("Added question: \n"+removeBlobForLogging(incoming).toString());
            return;
        }
        questions.add(incoming);
    }
    public void deleteQuestionById(int id) {
        LOG.info(String.valueOf(useData));
        if (useData) {
            question = dataService.findById(id);
            if(question == null){
                LOG.info("No such question with id: "+id);
                return;
            }
            LOG.info("Deleted question \n: "+removeBlobForLogging(question).toString());
            dataService.deleteById(id);
        }
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
    public void populateInitalUploadPhotos() throws IOException {
        if(loadCheck) { //1st load
            String[] photoContentRootPath= {"src/main/resources/photos/stop_sign.jpg","src/main/resources/photos/1_GiveWay.png","src/main/resources/photos/2_GiveWay.png"};
            byte[] filecontent = null;
            for(int i =0; i< photoContentRootPath.length; i++){
                File file = new File(photoContentRootPath[i]);
                FileInputStream inputStream = new FileInputStream(file);
                filecontent = IOUtils.toByteArray(inputStream);
                dataService.findById(i+1).setQuestion_photo(filecontent);
                dataService.save(getQuestion(i+1));
            }
            loadCheck=false;
        }
    }
    public byte[] inputImageToBytes(MultipartFile file){
        LOG.info("File name :" + file.getName() );
        LOG.info("File size :" + file.getSize() );
        LOG.info("File type :" + file.getContentType() );
        byte[] filecontent = null;
        try {
            InputStream inputStream = file.getInputStream();
            if(inputStream == null)
                LOG.info("file InputStream is null");
            filecontent = IOUtils.toByteArray(inputStream);
            return filecontent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Creating a copy of a question and replacing BLOB data, so it does not flood the console (replacing only notEmpty pictures)
    public Question removeBlobForLogging(Question question){
        Question questionSmall = new Question(question.getId(),question.getTopic(),question.getQuestion(),question.getAnswers(), question.getCorrect_answers(),question.getHint(), question.getQuestion_photo());
        if(questionSmall.getQuestion_photo().length > 0) questionSmall.setQuestion_photo("NotEmpty".getBytes());
        return questionSmall;
    }
}
