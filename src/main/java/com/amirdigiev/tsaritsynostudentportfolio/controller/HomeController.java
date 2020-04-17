package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.model.User;
import com.amirdigiev.tsaritsynostudentportfolio.service.FileService;
import com.amirdigiev.tsaritsynostudentportfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    public HomeController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping
    public String showHomePage(Model model) {
        User user = userService.getAnAuthorizedUser();
        fileService.setDefaultAvatar();
        model.addAttribute("currentUser", user);
        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getUsername());

        fileService.setDefaultAvatar();
        model.addAttribute("avatar", user.getAvatar());

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
