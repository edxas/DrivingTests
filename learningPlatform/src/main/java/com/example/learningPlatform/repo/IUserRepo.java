package com.example.learningPlatform.repo;

import com.example.learningPlatform.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends CrudRepository<Users,Long> {
    Users findByEmail(String email);
    Users findByUsername(String username);
}
