package com.amirdigiev.tsaritsynostudentportfolio.dao.user;

import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
}
