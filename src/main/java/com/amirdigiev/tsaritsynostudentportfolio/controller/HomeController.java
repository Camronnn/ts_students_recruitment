package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.model.User;
import com.amirdigiev.tsaritsynostudentportfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home/{id}")
    public String showHomePage(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        System.out.println(user);

        return "home";
    }

//    @GetMapping("/edit")
//    public String getEditUserDataForm(Model model) {
//        return "edit";
//    }
//
//    @PostMapping("/edit")
//    public String submitEditUserDataForm(@RequestParam String name,
//                                         @RequestParam String surname,
//                                         @RequestParam String patronymic,
//                                         @RequestParam
//                                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
//                                         @RequestParam String hometown,
//                                         @RequestParam String number,
//                                         @RequestParam String mail,
//                                         @RequestParam MultipartFile avatar,
//                                         Model model) throws IOException {
//        userService.update(name, surname,
//                patronymic, birthday,
//                hometown, number,
//                mail, avatar);
//
//        return "redirect:/home";
//    }
}
