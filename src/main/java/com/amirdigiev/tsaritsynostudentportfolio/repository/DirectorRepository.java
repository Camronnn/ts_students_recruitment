package com.amirdigiev.tsaritsynostudentportfolio.repository;

import com.amirdigiev.tsaritsynostudentportfolio.model.Director;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Long> {
    List<Director> findAll();
}
