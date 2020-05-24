package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Slf4j
@Controller
public class RedirectUserPageController {

    public final UserService userService;

    @Autowired
    public RedirectUserPageController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getUserPageById(@PathVariable Long id, Model model) {
        Optional<User> userId = userService.findById(id);
        model.addAttribute("userId", userId);

        return "user_page";
    }
}
