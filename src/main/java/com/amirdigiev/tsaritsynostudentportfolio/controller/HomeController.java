package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.dao.certificate.CertificateService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.student.StudentService;
import com.amirdigiev.tsaritsynostudentportfolio.model.*;
import com.amirdigiev.tsaritsynostudentportfolio.dao.file.FileService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Controller
public class HomeController {

    private final UserService userService;
    private final StudentService studentService;
    private final FileService fileService;
    private final CertificateService certificateService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${application.avatar-folder}")
    private String avatarFolder;

    @Value("${application.certificate-folder}")
    private String certificateFolder;

    @Autowired
    public HomeController(UserService userService,
                          FileService fileService,
                          BCryptPasswordEncoder passwordEncoder,
                          StudentService studentService,
                          CertificateService certificateService) {
        this.userService = userService;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
        this.studentService = studentService;
        this.certificateService = certificateService;
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        User currentUser = userService.getAnAuthorizedUser();
        Object userRole = userService.defineRoleByUserId();

        if (userRole instanceof Student) {
            Student student = (Student) userRole;
            model.addAttribute("faculty", student.getFaculty());
            model.addAttribute("groupNumber", student.getGroupNumber());
            model.addAttribute("rating", student.getRating());
            model.addAttribute("certificates", student.getCertificates());
        }

        if (userRole instanceof Admin) {
           Admin admin = (Admin) userRole;
           List<Certificate> certificates = certificateService.findAll();
           model.addAttribute("certificates", certificates);
        }

        if (userRole instanceof Director) {
            Director director = (Director) userRole;
            model.addAttribute("department", director.getDepartment());
        }

        if (userRole instanceof HrManager) {
            HrManager manager = (HrManager) userRole;
            model.addAttribute("company", manager.getCompany());
        }

        fileService.setDefaultAvatar();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("name", currentUser.getName());
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("avatar", currentUser.getAvatar());
        model.addAttribute("surname", currentUser.getSurname());

        return "home";
    }

    @GetMapping("/edit")
    public String getEditUserDataForm(Model model) {
        User currentUser = userService.getAnAuthorizedUser();

        model.addAttribute("currentUser", currentUser);

        Object userRole = userService.defineRoleByUserId();
        if (userRole instanceof Student) {
            Student student = (Student) userRole;
            model.addAttribute("student", student);
        }
        if (userRole instanceof Director) {
            Director director = (Director) userRole;
            model.addAttribute("director", director);
        }
        if (userRole instanceof HrManager) {
            HrManager hrManager = (HrManager) userRole;
            model.addAttribute("manager", hrManager);
        }

        return "edit";
    }

    @PostMapping("/edit")
    public String submitEditUserDataForm(@ModelAttribute User currentUser,
                                         MultipartFile avatar,
                                         Model model) throws IOException {
        fileService.uploadImg(avatar, Paths.get(avatarFolder));
        currentUser.setAvatar(avatar.getOriginalFilename());
        userService.add(currentUser);

        return "redirect:/home";
    }

    @PostMapping("/delete_avatar")
    public String deleteAvatar() {
        fileService.deleteAvatar();
        return "redirect:/home";
    }

    @GetMapping("/add_certificate")
    public String getCertificateAddForm(Model model) {
        model.addAttribute("certificate", new Certificate());
        return "certificate_add_form";
    }

    @PostMapping("/add_certificate")
    public String confirmAddingCertificate(@ModelAttribute("certificate") Certificate certificate,
                                           @RequestParam MultipartFile certificateImg) throws IOException {

        certificate.setCertificateImage(fileService.uploadImg(certificateImg, Paths.get(certificateFolder)));

        User currentUser = userService.getAnAuthorizedUser();
        if (currentUser.getRole().equals("STUDENT")) {
            List<Student> students = studentService.findAll();
            for(Student student : students) {
                if(currentUser.getId() == student.getUser().getId()) {
                    student.getCertificates().add(certificate);
                    certificate.setStudent(student);
                }
            }
        }
        certificateService.add(certificate);

        return "redirect:/home";
    }

    @PostMapping("/delete_certificate/{id}")
    public String deleteCertificate(@PathVariable("id") Long id) {
        Optional<Certificate> existingCertificate = certificateService.findById(id);
        Student student = existingCertificate.get().getStudent();
        studentService.decreaseRating(student.getId());

        existingCertificate.ifPresent(
                certificate -> certificateService.deleteById(certificate.getId())
        );

        return "redirect:/home";
    }
}
