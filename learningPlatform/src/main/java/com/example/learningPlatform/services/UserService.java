package com.example.learningPlatform.services;

import com.example.learningPlatform.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users updateUser(Users user) {
        return userRepository.save(user);
    }

    public String deleteUser(Long userId) {
        Optional<Users> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            String salt = String.valueOf(user.getSalt());
            userRepository.deleteById(userId);
            return salt;
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }
}
