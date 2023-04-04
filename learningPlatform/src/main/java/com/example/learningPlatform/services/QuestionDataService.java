package com.example.learningPlatform.services;

import com.example.learningPlatform.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDataService extends CrudRepository<Question,Integer> {
}
