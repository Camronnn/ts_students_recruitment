package com.amirdigiev.tsaritsynostudentportfolio.repository;


import com.amirdigiev.tsaritsynostudentportfolio.model.Moderator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeratorRepository extends CrudRepository<Moderator, Long> {
    List<Moderator> findAll();
}
