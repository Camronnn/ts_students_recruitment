package com.amirdigiev.tsaritsynostudentportfolio.dao.student;

import com.amirdigiev.tsaritsynostudentportfolio.model.role.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findAll();
}
