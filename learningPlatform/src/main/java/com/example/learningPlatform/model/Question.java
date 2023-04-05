package com.example.learningPlatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Table(name = "question")
@Data //no
@Entity
@NoArgsConstructor //no
@AllArgsConstructor //no
public class Question {
    @Id //generated value strategy type indentity
    //@Setter(value = AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private int id;
    @Column(name = "Topic")
    //@NotBlank(message = "Not blank")
    private Topic topic = Topic.signs;
    @Column(name = "Question")
    @NotBlank(message = "Not blank")
    //@Size(min = 3,max = 30, message = "Out of range min 3 un max 30")
    //@Pattern(regexp = "[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*+[- ]{1}+[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*|[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*", message = "examples June or Mary Barbara")
    private String question;
    @Column(name = "Answers")
    @NotBlank(message = "Not blank")
    //@Size(min = 3,max = 30, message = "Out of range min 3 un max 30")
    //@Pattern(regexp = "[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*+[- ]{1}+[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*|[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}+[a-zāčēģīķļņšūž]*", message = "examples Alien or Harding-Rolls")
    private String answers;
    @Column(name = "CorrectAnswers")
    @NotBlank(message = "Not blank")
    //@Pattern(regexp = "^[a-zA-Z0-9]{5,16}", message = "From 6 to 16 numbers and alphabet")
    private String correct_answers;
}