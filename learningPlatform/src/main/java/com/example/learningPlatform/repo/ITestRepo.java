package com.example.learningPlatform.repo;


import com.example.learningPlatform.model.Tests;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ITestRepo extends CrudRepository<Tests,Integer> {
    Tests findTopByOrderByIdDesc();
    ArrayList<Tests> findAllByUseridId(int id);
    ArrayList<Tests> findAllByUseridIdAndTopic(int id,String topic);
    ArrayList<Tests> findTop2ByUseridIdAndTopicOrderByIdDesc(int id,String topic);
    ArrayList<Tests> findTop10ByUseridIdOrderByIdDesc(int id);
}
