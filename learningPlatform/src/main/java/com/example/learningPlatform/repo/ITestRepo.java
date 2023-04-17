package com.example.learningPlatform.repo;


import com.example.learningPlatform.model.Tests;
import org.springframework.data.repository.CrudRepository;

public interface ITestRepo extends CrudRepository<Tests,Integer> {
    Tests findTopByOrderByIdDesc();
}
