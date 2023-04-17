package com.example.learningPlatform.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "tests")
@Entity
@Data
@NoArgsConstructor
public class Tests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private int id ;
    @Column(name = "user_chosen_answer_list")
    private ArrayList<String> user_chosen_answer_list;
    @Column(name = "score")
    private double score;
    @Column(name = "topic")
    private String topic;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private Users user_id;

    @ManyToMany
    @JoinTable(
            name = "question_test",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Questions> questionsList= new ArrayList<>();

    public Tests(ArrayList<String> userChosenAnswerList, double score, String topic, ArrayList<Questions> questions, Users user){
        this.score = score;
        this.questionsList = questions;
        this.topic = topic;
        this.user_id = user;
        this.user_chosen_answer_list = userChosenAnswerList;
    }
    public Tests(  String topic, ArrayList<Questions> questions, Users user){

        this.questionsList = questions;
        this.topic = topic;
        this.user_id = user;
    }
    public Tests(  String topic,  Users user){


        this.topic = topic;
        this.user_id = user;
    }


}
