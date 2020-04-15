package com.amirdigiev.tsaritsynostudentportfolio.repository;

import com.amirdigiev.tsaritsynostudentportfolio.model.Student;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository<U extends Student> extends CommonRepository<U> {

}
