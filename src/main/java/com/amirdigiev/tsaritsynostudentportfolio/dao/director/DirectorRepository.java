package com.amirdigiev.tsaritsynostudentportfolio.dao.director;

import com.amirdigiev.tsaritsynostudentportfolio.model.role.Director;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Long> {
    List<Director> findAll();
}
