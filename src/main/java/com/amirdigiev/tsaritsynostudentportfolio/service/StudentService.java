package com.amirdigiev.tsaritsynostudentportfolio.service;

import com.amirdigiev.tsaritsynostudentportfolio.repostitory.StudentRepository;
import com.amirdigiev.tsaritsynostudentportfolio.model.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void update(Student newStudent) {
    }

    public String delete(Long id) {
        if(studentRepository.getStudentById(id) != null) {
            studentRepository.deleteById(id);
            return "Пользователь под id " + "id" + " успешно удалён";
        }

        return "Такого пользователя не существует!";
    }

}
