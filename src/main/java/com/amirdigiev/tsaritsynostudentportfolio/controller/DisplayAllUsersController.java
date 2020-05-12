package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.dao.director.DirectorService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.manager.HrManagerService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.student.StudentService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.Director;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.HrManager;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.Student;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class DisplayAllUsersController {

    private final UserService userService;
    private final StudentService studentService;
    private final DirectorService directorService;
    private final HrManagerService hrManagerService;

    @Autowired
    public DisplayAllUsersController(UserService userService,
                                     StudentService studentService,
                                     DirectorService directorService,
                                     HrManagerService hrManagerService)
    {
        this.userService = userService;
        this.studentService = studentService;
        this.directorService = directorService;
        this.hrManagerService = hrManagerService;
    }

    @GetMapping("/all_users")
    public String showAllUser(Model model) {
        List<User> users = userService.findAll();
        User currentUser = userService.getAnAuthorizedUser();

        model.addAttribute("users", users);
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("avatar", currentUser.getAvatar());
        model.addAttribute("role", currentUser.getRole());

        return "all_users";
    }

    @PostMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            switch (user.get().getRole()) {
                case "STUDENT":
                    List<Student> students = studentService.findAll();
                    for(Student student : students) {
                        if (student.getUser().getId().equals(user.get().getId()))
                            studentService.deleteById(student.getId());
                    }
                case "DIRECTOR":
                    List<Director> directors = directorService.findAll();
                    for(Director director : directors) {
                        if (director.getUser().getId().equals(user.get().getId()))
                            directorService.deleteById(director.getId());
                    }
                case "MANAGER":
                    List<HrManager> managers = hrManagerService.findAll();
                    for(HrManager manager : managers) {
                        if (manager.getUser().getId().equals(user.get().getId()))
                            hrManagerService.deleteById(manager.getId());
                    }
            }
        }

        userService.deleteById(user.get().getId());
        return "redirect:/all_users";
    }
}
