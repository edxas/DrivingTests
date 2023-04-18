package com.example.learningPlatform.repo;

import com.example.learningPlatform.model.Users;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



import java.util.Optional;

@Repository
public interface IUserRepo extends CrudRepository<Users, Integer> {
    Users findByEmail(String email);
    Users findByUsername(String username);

}
