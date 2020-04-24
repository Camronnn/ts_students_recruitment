package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.model.Director;
import com.amirdigiev.tsaritsynostudentportfolio.model.HrManager;
import com.amirdigiev.tsaritsynostudentportfolio.model.Student;
import com.amirdigiev.tsaritsynostudentportfolio.model.User;
import com.amirdigiev.tsaritsynostudentportfolio.service.FileService;
import com.amirdigiev.tsaritsynostudentportfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;


@Controller
public class HomeController {

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    public HomeController(UserService userService,
                          FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        User currentUser = userService.getAnAuthorizedUser();
        Object userRole = userService.defineRoleByUserId();

        if(userRole instanceof Student) {
            Student student = (Student) userRole;
            model.addAttribute("faculty", student.getFaculty());
            model.addAttribute("groupNumber", student.getGroupNumber());
            model.addAttribute("rating", student.getRating());
        }

        if(userRole instanceof Director) {
            Director director = (Director) userRole;
            model.addAttribute("department", director.getDepartment());
        }

        if(userRole instanceof HrManager) {
            HrManager manager = (HrManager) userRole;
            model.addAttribute("company", manager.getCompany());
        }

        fileService.setDefaultAvatar();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("name", currentUser.getName());
        model.addAttribute("surname", currentUser.getSurname());

        return "home";
    }

    @GetMapping("/edit")
    public String getEditUserDataForm(Model model) {
        return "edit";
    }

    @PostMapping("/edit")
    public String submitEditUserDataForm(@RequestParam String name,
                                         @RequestParam String surname,
                                         @RequestParam String patronymic,
                                         @RequestParam
                                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
                                         @RequestParam String hometown,
                                         @RequestParam String number,
                                         @RequestParam String mail,
                                         @RequestParam MultipartFile avatar,
                                         Model model) throws IOException {
        userService.update(name, surname,
                patronymic, birthday,
                hometown, number,
                mail, avatar);

        return "redirect:/home";
    }
}
