package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.component.Converter;
import com.amirdigiev.tsaritsynostudentportfolio.dao.admin.AdminService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.director.DirectorService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.manager.HrManagerService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.moderator.ModeratorService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.student.StudentService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.dto.SuperUserDto;
import com.amirdigiev.tsaritsynostudentportfolio.dao.security.SecurityService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@SuppressWarnings("Duplicates")
public class RegistrationController {

    private final StudentService studentService;
    private final DirectorService directorService;
    private final AdminService adminService;
    private final ModeratorService moderatorService;
    private final HrManagerService hrManagerService;
    private final SecurityService securityService;
    private final UserService userService;

    private final Converter converter;

    @Autowired
    public RegistrationController(SecurityService securityService,
                                  StudentService studentService,
                                  DirectorService directorService,
                                  UserService userService,
                                  Converter converter,
                                  AdminService adminService,
                                  ModeratorService moderatorService,
                                  HrManagerService hrManagerService)
    {
        this.securityService = securityService;
        this.studentService = studentService;
        this.directorService = directorService;
        this.userService = userService;
        this.converter = converter;
        this.adminService = adminService;
        this.moderatorService = moderatorService;
        this.hrManagerService = hrManagerService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getUserRegistrationPage(Model model) {
        SuperUserDto userDto = new SuperUserDto();
        model.addAttribute("newUser", userDto);

        return "registration";
    }

    @PostMapping("/registration")
    public String registerAccount(@ModelAttribute("newUser") @Valid SuperUserDto newUser,
                                          BindingResult bindingResult,
                                          Model model)
    {
        log.info("New user: " + newUser.toString() + " verification");
        if(bindingResult.hasErrors()) {
            log.error("Validation errors: " + bindingResult.hasErrors());
            return "registration";
            }

        if(!newUser.getPassword().equals(newUser.getMatchingPassword())) {
            log.error("Пароли не совпадают: " + newUser.getPassword() + " != " + newUser.getMatchingPassword());
            model.addAttribute("passwordError", "Пароли не совпадают!");
            return "registration";
        }

        User user = converter.toEntity(newUser);
        userService.add(user);
        userService.addUserWithRole(user);

        securityService.autoLogin(newUser.getUsername(), newUser.getMatchingPassword());
        log.info("New user registered: " + newUser.toString());

        return "redirect:/home";
    }
}