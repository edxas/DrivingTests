package com.example.learningPlatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;


@Table(name = "questions")
@Entity
@Data
//@Component
@NoArgsConstructor
@AllArgsConstructor
public class Question {
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
    private String correct_answers;
    @Column(name = "Hint", nullable = false)
    private String hint;
    @Lob
    @Column(name="Question_Photo")
    private byte[] question_photo;
}