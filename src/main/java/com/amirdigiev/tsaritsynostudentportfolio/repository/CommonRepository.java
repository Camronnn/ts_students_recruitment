package com.amirdigiev.tsaritsynostudentportfolio.repository;

import com.amirdigiev.tsaritsynostudentportfolio.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface CommonRepository<U extends User> extends CrudRepository<U, Long> {

    U findByUsername(String username);
    Optional<U> findById(Long id);
    List<U> findAll();
    void deleteById(Long id);
}
