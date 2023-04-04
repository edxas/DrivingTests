package com.example.learningPlatform;

import com.example.learningPlatform.model.Role;
import com.example.learningPlatform.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrivingLearningPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrivingLearningPlatformApplication.class, args);
		User newUser = new User("Jonas",  "Kazlauskas", Role.admin, "jonas@gmail.com", "felix", "Radmin1$");
		System.out.println(newUser);
	}

}
