package com.example.learningPlatform.service.impl;

import com.example.learningPlatform.model.Role;
import com.example.learningPlatform.model.Salt;
import com.example.learningPlatform.model.Users;
import com.example.learningPlatform.repo.ISaltRepo;
import com.example.learningPlatform.repo.IUserRepo;
import com.example.learningPlatform.service.IDrivingLearningPlatformService;
import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.JDBCType;

import static com.example.learningPlatform.model.SHA512Hasher.*;

@Service
@Data
@Slf4j
public class IDrivingLearningPlatformServiceImpl implements IDrivingLearningPlatformService {


    @Autowired
    IUserRepo userRepo;
    @Autowired
    ISaltRepo saltRepo;

    public Users usersStatic = null;



    @Override
    public boolean newUser(Users user) throws NoSuchAlgorithmException {
        Users users1 = userRepo.findByEmail(user.getEmail());
        Users users2 = userRepo.findByUsername(user.getUsername());
        if(users1 ==null && users2 == null ){
//
            Salt salt = new Salt(getSalt());
            String password1 = getSecurePassword(user.getPassword(), salt.getSalt());
            LOG.info(
                    userRepo.save(new Users(user.getName(), user.getSurname(), Role.user.name(), user.getEmail(), user.getUsername(),password1, user.getPassword(),salt))
                            .toString());
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
        if (usersStatic != null){
            Users users1 = userRepo.findByUsername(usersStatic.getUsername());
            return users1;
        }

        return new Users("test","test",Role.guest.name(), "tesr@gmail.com","test","test","Test5$66",new Salt());
    }



    @Override
    public void logOut() {
        usersStatic = null;
    }

    @Override
    public boolean editUser(Users user) {
        Users users1 = userRepo.findByUsername(user.getUsername());

        if(users1 !=null  ){


            users1.setPassword("Testt66$");
            users1.setName(user.getName());
            users1.setSurname(user.getSurname());
            users1.setEmail(user.getEmail());
            userRepo.save(users1);
            return true;
        }
        return false;
    }

    @Override
    public Users getUserById(int id) {
        return userRepo.findById(id).get();
    }

    @Override
    public boolean changePassword(String newPassword, String username, String password) throws NoSuchAlgorithmException {

        System.out.println("Old password "+password);
        System.out.println("New password "+newPassword);
        if(authoriseUser(username,password)){
            Users users1 = userRepo.findByUsername(username);
            Salt salt = new Salt(getSalt());
            String password1 = getSecurePassword(newPassword, salt.getSalt());
            users1.setPassword(newPassword);
            users1.setSalt(salt);
            users1.setHashPassword(password1);
            userRepo.save(users1);

            return true;
        }
        return false;
    }
}
