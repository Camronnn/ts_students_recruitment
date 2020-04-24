package com.amirdigiev.tsaritsynostudentportfolio.repository;

import com.amirdigiev.tsaritsynostudentportfolio.model.HrManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrManagerRepository extends CrudRepository<HrManager, Long> {
    List<HrManager> findAll();
}
