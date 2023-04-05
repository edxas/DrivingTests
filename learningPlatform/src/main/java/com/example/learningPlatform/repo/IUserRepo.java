package com.example.learningPlatform.repo;

import com.example.learningPlatform.model.Users;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends CrudRepository<Users, VarcharJdbcType> {
    Users findByEmail(String email);
    Users findByUsername(String username);
    Users findTopByOrderByIdDesc();

}
