package com.example.learningPlatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "questions")
@Data
@Entity
@NoArgsConstructor
public class Question {
    @Id
    //@Setter(value = AccessLevel.NONE)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private int id;
    @Column(name = "Topic")
    @NotBlank(message = "Not blank")
    private Topic role = Topic.signs;
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