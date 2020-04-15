package com.amirdigiev.tsaritsynostudentportfolio.repository;

import com.amirdigiev.tsaritsynostudentportfolio.model.HrManager;
import org.springframework.stereotype.Repository;

@Repository
public interface HrManagerRepository<U extends HrManager> extends CommonRepository<U> {

}
