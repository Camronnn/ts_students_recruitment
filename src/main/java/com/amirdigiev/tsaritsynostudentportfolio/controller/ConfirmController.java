package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.dao.certificate.CertificateService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.student.StudentService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.Certificate;
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
public class ConfirmController {

    private final UserService userService;
    private final StudentService studentService;
    private final CertificateService certificateService;

    @Autowired
    public ConfirmController(UserService userService,
                             CertificateService certificateService,
                             StudentService studentService)
    {
        this.userService = userService;
        this.certificateService = certificateService;
        this.studentService = studentService;
    }

    @GetMapping("/confirm_certificates")
    public String getConfirmPage(Model model) {
        User currentUser = userService.getAnAuthorizedUser();
        List<Certificate> certificates = certificateService.findAll();

        model.addAttribute("avatar", currentUser.getAvatar());
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("role", currentUser.getRole());
        model.addAttribute("certificates", certificates);

        return "confirm_certificates";
    }

    @PostMapping("/confirm/{id}")
    public String confirm(@PathVariable("id") Long id) {
        Optional<Certificate> certificate = certificateService.findById(id);
        if (certificate.isPresent()) {
            certificate.get().setApproved(true);
            Student student = certificate.get().getStudent();
            student.setRating(student.getRating() + 5);

            certificateService.add(certificate.get());
            studentService.add(student);
        }

        return "redirect:/confirm_certificates";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        certificateService.deleteById(id);
        return "redirect:/confirm_certificates";
    }
}
