package com.amirdigiev.tsaritsynostudentportfolio.service;

import com.amirdigiev.tsaritsynostudentportfolio.model.Student;
import com.amirdigiev.tsaritsynostudentportfolio.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final CommonRepository<Student> commonRepository;

    @Autowired
    public StudentService(@Qualifier("studentRepository") CommonRepository<Student> commonRepository) {
        this.commonRepository = commonRepository;
    }

    public Student add(Student student) {
        return commonRepository.save(student);
    }

    public Optional<Student> findById(Long id) {
        return commonRepository.findById(id);
    }
}
