package com.amirdigiev.tsaritsynostudentportfolio.controller;

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

import javax.validation.Valid;

@Slf4j
@Controller
public class RegistrationController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public RegistrationController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        log.info("showRegistrationForm method called");
        model.addAttribute("newUser", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("newUser") @Valid User newUser,
                                      BindingResult bindingResult,
                                      Model model) {
        log.info("registerUserAccount method called");
        if(bindingResult.hasErrors()) {
            log.error("Validation errors: " + bindingResult.hasErrors());
            return "registration";
        }

        if(!newUser.getPassword().equals(newUser.getMatchingPassword())) {
            log.error("Пароли не совпадают: " + newUser.getPassword() + " != " + newUser.getMatchingPassword());
            model.addAttribute("passwordError", "Пароли не совпадают!");
            return "registration";
        }

        userService.add(newUser);
//        securityService.autoLogin(newUser.getUsername(), newUser.getMatchingPassword());
        log.info("Новый пользователь зарегистрирован: " + newUser.toString());

        return "redirect:/login";
    }
}