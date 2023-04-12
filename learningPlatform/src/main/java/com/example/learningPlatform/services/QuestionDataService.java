package com.example.learningPlatform.services;

import com.example.learningPlatform.model.Question;
import com.example.learningPlatform.model.Users;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDataService extends CrudRepository<Question, IntegerJdbcType> {
    Question findById(int id);
    void deleteById(int id);
}
