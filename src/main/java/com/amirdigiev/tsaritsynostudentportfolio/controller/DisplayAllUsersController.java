package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.dao.certificate.CertificateService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.director.DirectorService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.manager.HrManagerService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.student.StudentService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class DisplayAllUsersController {

    private final UserService userService;
    private final StudentService studentService;
    private final DirectorService directorService;
    private final HrManagerService hrManagerService;
    private final CertificateService certificateService;

    @Autowired
    public DisplayAllUsersController(UserService userService,
                                     StudentService studentService,
                                     DirectorService directorService,
                                     HrManagerService hrManagerService,
                                     CertificateService certificateService)
    {
        this.userService = userService;
        this.studentService = studentService;
        this.directorService = directorService;
        this.hrManagerService = hrManagerService;
        this.certificateService = certificateService;
    }

    @GetMapping("/all_users")
    public String showAllUser(Model model) {
        List<User> users = userService.findAll();
        User currentUser = userService.getAnAuthorizedUser();

        List<Student> studentList = studentService.findAll();
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return student1.getRating() > student2.getRating() ? -1
                        : (student1.getRating() < student2.getRating()) ? 1 : 0;
            }
        });

        List<User> students = new ArrayList<>();
        for (Student student : studentList) {
            students.add(student.getUser());
        }

        List<User> directors = users.stream()
                .filter(user -> user.getRole().equals("DIRECTOR"))
                .collect(Collectors.toList());

        List<User> managers = users.stream()
                .filter(user -> user.getRole().equals("MANAGER"))
                .collect(Collectors.toList());

        List<Student> studentsList = studentService.findAll();

        model.addAttribute("students", students);
        model.addAttribute("directors", directors);
        model.addAttribute("managers", managers);
        model.addAttribute("studentsList", studentsList);
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("avatar", currentUser.getAvatar());
        model.addAttribute("role", currentUser.getRole());

        return "all_users";
    }

    @PostMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable Long id) {
        User currentUser = userService.getAnAuthorizedUser();
        Optional<User> user = userService.findById(id);
        userService.removeUserRole(id);
        userService.deleteById(user.get().getId());

        log.info(currentUser.getUsername() + " удалил пользователя " + user.get().getUsername());
        return "redirect:/all_users";
    }
}
