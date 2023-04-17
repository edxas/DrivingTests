package com.example.learningPlatform.services;

import com.example.learningPlatform.model.Questions;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface QuestionDataService extends CrudRepository<Questions, Integer> {
    Questions findById(int id);
    void deleteById(int id);

    ArrayList<Questions> findAll();
    ArrayList<Questions> findAllByTestslistId(int id);
}
