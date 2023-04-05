package com.example.learningPlatform.service.impl;

import com.example.learningPlatform.model.Role;
import com.example.learningPlatform.model.Salt;
import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.repo.ISaltRepo;
import com.example.learningPlatform.repo.IUserRepo;
import com.example.learningPlatform.service.IDrivingLearningPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

import static com.example.learningPlatform.model.SHA512Hasher.*;

@Service
public class IDrivingLearningPlatformServiceImpl implements IDrivingLearningPlatformService {

    @Autowired
    IUserRepo userRepo;
    @Autowired
    ISaltRepo saltRepo;

    public Users usersStatic;



    @Override
    public boolean newUser(Users user) throws NoSuchAlgorithmException {

        Users users1 = userRepo.findByEmail(user.getEmail());


        if(users1 ==null ){

            Salt salt = new Salt(getSalt());
            String password1 = getSecurePassword(user.getPassword(), salt.getSalt());

            userRepo.save(new Users(user.getName(), user.getSurname(), Role.user.name(), user.getEmail(), user.getUsername(),password1, user.getPassword(),salt));

            return true;
        }
        return false;
    }

    @Override
    public boolean authoriseUser(String username, String password) {
        Users users1 = userRepo.findByUsername(username);


        if(users1 !=null){


            if (checkPassword(users1.getHashPassword(),password, users1.getSalt().getSalt())){
                usersStatic = users1;
                return true;
            }

        }
        return false;
    }

    @Override
    public Users getAuthorisedUser() {
        Users users1 = userRepo.findByEmail(usersStatic.getEmail());
        return users1;
    }
}
