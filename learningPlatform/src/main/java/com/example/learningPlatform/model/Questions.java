package com.example.learningPlatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Table(name = "questions")
@Entity(name = "questions")
@Data
//@Component
@NoArgsConstructor
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private int id;
    @Column(name = "Topic", nullable = false, length = 25)
    private String topic;
    @Column(name = "Question", nullable = false)
    private String question;
    @Column(name = "Answers", nullable = false)
    private String[] answers;
    @Column(name = "Correct_Answers", nullable = false)
    private String[] correct_answers;
    @Column(name = "Hint", nullable = false)
    private String hint;
    @Lob
    @Column(name="Question_Photo")
    private byte[] question_photo;

    @ManyToMany(mappedBy = "questionsList")
    private List<Tests> testslist  = new ArrayList<>();

    public Questions(String Topic, String Question, String[] Answers, String[] Correct_answers, String Hint, byte[] Question_photo){
        this.answers = Answers;
        this.question = Question;
        this.correct_answers = Correct_answers;
        this.hint = Hint;
        this.question_photo = Question_photo;
        this.topic = Topic;
    }


}