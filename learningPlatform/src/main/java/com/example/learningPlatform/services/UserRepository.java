package com.example.learningPlatform.services;

import com.example.learningPlatform.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    Users findById(long id);

    Users findByEmail(String email);

}