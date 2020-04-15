package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.model.Roles;
import com.amirdigiev.tsaritsynostudentportfolio.model.Student;
import com.amirdigiev.tsaritsynostudentportfolio.model.User;
import com.amirdigiev.tsaritsynostudentportfolio.service.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.service.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegistrationController<U extends User> {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public RegistrationController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

//    @GetMapping("/select_role")
//    public String showRoleSelectionForm(@RequestParam U student,
//                                        @RequestParam U hrManager,
//                                        @RequestParam U Director,
//                                        Model model) {
//
//    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        User newUser = new Student();
        log.info("showRegistrationForm method called");
        model.addAttribute("newUser", newUser);

        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute @Valid U newUser,
                                      BindingResult bindingResult,
                                      Model model) {
        log.info("registerUserAccount method called");
        if(bindingResult.hasErrors()) {
            log.error("Validation errors: " + bindingResult.hasErrors());
            return "registration";
        }

        if(!newUser.getPassword().equals(newUser.getMatchingPassword())) {
            log.error("Passwords do not match: " + newUser.getPassword() + " != " + newUser.getMatchingPassword());
            model.addAttribute("passwordError", "Пароли не совпадают!");
            return "registration";
        }

        if(userService.findById(newUser.getId()).isPresent()) {
            log.error("A user with the same name already exists");
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует!");

            return "registration";
        }

        userService.checkUserAuthorities(newUser);
        securityService.autoLogin(newUser.getUsername(), newUser.getMatchingPassword());
        log.info("New user registered: " + newUser.toString());

        return "redirect:/login";
    }
}