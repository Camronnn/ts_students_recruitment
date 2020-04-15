package com.amirdigiev.tsaritsynostudentportfolio.repository;

import com.amirdigiev.tsaritsynostudentportfolio.model.Director;
import org.springframework.stereotype.Repository;


@Repository
public interface DirectorRepository<U extends Director> extends CommonRepository<U> {

}
