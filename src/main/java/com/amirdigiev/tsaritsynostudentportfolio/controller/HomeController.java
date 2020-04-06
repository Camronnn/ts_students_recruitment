package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.model.Student;
import com.amirdigiev.tsaritsynostudentportfolio.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final StudentService studentService;

    @Autowired
    public HomeController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/home/{id}")
    public String getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        System.out.println(student);
        return "home";
    }
}
