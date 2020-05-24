package com.amirdigiev.tsaritsynostudentportfolio.controller;

import com.amirdigiev.tsaritsynostudentportfolio.component.EmailSender;
import com.amirdigiev.tsaritsynostudentportfolio.component.GeneratorDocxFile;
import com.amirdigiev.tsaritsynostudentportfolio.dao.admin.AdminService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.certificate.CertificateService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.director.DirectorService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.manager.HrManagerService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.student.StudentService;
import com.amirdigiev.tsaritsynostudentportfolio.model.*;
import com.amirdigiev.tsaritsynostudentportfolio.dao.file.FileService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Controller
public class HomeController {

    private final UserService userService;
    private final StudentService studentService;
    private final DirectorService directorService;
    private final AdminService adminService;
    private final HrManagerService hrManagerService;

    private final FileService fileService;
    private final CertificateService certificateService;

    private final BCryptPasswordEncoder passwordEncoder;
    private final GeneratorDocxFile generatorDocxFile;
    private final EmailSender emailSender;

    @Value("${application.avatar-folder}")
    private String avatarFolder;

    @Value("${application.certificate-folder}")
    private String certificateFolder;

    @Autowired
    public HomeController(UserService userService,
                          FileService fileService,
                          BCryptPasswordEncoder passwordEncoder,
                          StudentService studentService,
                          CertificateService certificateService,
                          GeneratorDocxFile generatorDocxFile,
                          DirectorService directorService,
                          AdminService adminService,
                          HrManagerService hrManagerService,
                          EmailSender emailSender)
    {
        this.userService = userService;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
        this.studentService = studentService;
        this.certificateService = certificateService;
        this.generatorDocxFile = generatorDocxFile;
        this.directorService = directorService;
        this.adminService = adminService;
        this.hrManagerService = hrManagerService;
        this.emailSender = emailSender;
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        User currentUser = userService.getAnAuthorizedUser();
        log.info(currentUser.getUsername() + " switched to /home");
        log.info(currentUser.getUsername() + " is a " + currentUser.getRole());

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
        model.addAttribute("role", currentUser.getRole());
        model.addAttribute("name", currentUser.getName());
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("avatar", currentUser.getAvatar());
        model.addAttribute("surname", currentUser.getSurname());

        return "home";
    }

    @GetMapping("/edit")
    public String getEditUserDataForm(Model model) {
        User editableUser = userService.getAnAuthorizedUser();
        log.info(editableUser.getUsername() + " switched to /edit");

        model.addAttribute("editableUser", editableUser);

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
    public String submitEditUserDataForm(@ModelAttribute("editableUser") User editableUser,
                                         @ModelAttribute("student") Student student,
                                         @ModelAttribute("director") Director director,
                                         @ModelAttribute("manager") HrManager manager,
                                         @RequestParam MultipartFile img,
                                         Model model) throws IOException
    {
        User currentUser = userService.getAnAuthorizedUser();

        fileService.uploadImg(img, Paths.get(avatarFolder));
        editableUser.setAvatar(img.getOriginalFilename());
        userService.update(editableUser);

        if (currentUser.getRole().equals("STUDENT")) {
            student.setUser(editableUser);
            studentService.update(student);
            log.info("Student " + student.getId() + " edited");
        }

        if (currentUser.getRole().equals("DIRECTOR")) {
            director.setUser(editableUser);
            directorService.update(director);
            log.info("Director " + director.getId() + " edited");
        }

        if (currentUser.getRole().equals("MANAGER")) {
            manager.setUser(editableUser);
            hrManagerService.update(manager);
            log.info("Manager " + manager.getId() + " edited");
        }

        log.info("Current user " + editableUser.getUsername() + " edited");

        return "redirect:/home";
    }

    @GetMapping("docx_form")
    public String getCreateDocxForm(Model model) {
        log.info(userService.getAnAuthorizedUser().getUsername() + " switched to /docx_form");
        User currentUser = userService.getAnAuthorizedUser();

        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("avatar", currentUser.getAvatar());
        return "docx_form";
    }

    @PostMapping("/docx_form")
    public String submitCreateDocxForm(@RequestParam String education,
                                       @RequestParam String specialty,
                                       @RequestParam String start,
                                       @RequestParam String end,
                                       @RequestParam String additionalEducation) {
        User currentUser = userService.getAnAuthorizedUser();

        List<Student> students = studentService.findAll();
        for (Student student : students) {
            if (student.getUser().getId().equals(currentUser.getId())) {
                try {
                    generatorDocxFile.createPortfolio(
                            student,
                            education,
                            specialty,
                            start,
                            end,
                            additionalEducation);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return "redirect:/home";
    }

    @GetMapping("/send_mail")
    public String getSendMailForm(Model model) {
        model.addAttribute("username", userService.getAnAuthorizedUser().getUsername());
        model.addAttribute("avatar", userService.getAnAuthorizedUser().getAvatar());

        return "send_mail";
    }

    @PostMapping("/send_mail")
    public String submitSendMailForm(@RequestParam String subject,
                                     @RequestParam String text) {
        emailSender.sendSimpleMessage(subject, text);
        return "redirect:/home";
    }

    @PostMapping("/delete_avatar")
    public String deleteAvatar() {
        log.info(userService.getAnAuthorizedUser().getUsername() + " delete avatar");
        fileService.deleteAvatar();
        return "redirect:/home";
    }

    @GetMapping("/add_certificate")
    public String getCertificateAddForm(Model model) {
        log.info(userService.getAnAuthorizedUser().getUsername() + " switched to /add_certificates");
        model.addAttribute("certificate", new Certificate());
        return "certificate_add_form";
    }

    @PostMapping("/add_certificate")
    public String confirmAddingCertificate(@ModelAttribute("certificate") Certificate certificate,
                                           @RequestParam MultipartFile certificateImg) throws IOException
    {
        log.info(userService.getAnAuthorizedUser().getUsername() + " switched to /add_certificate");
        certificate.setCertificateImage(fileService.uploadImg(certificateImg, Paths.get(certificateFolder)));
        log.info("new certificate uploaded: " + certificateImg.getOriginalFilename());

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
        log.info(userService.getAnAuthorizedUser().getUsername()
                + " added new certificate: " + certificate.getCompetency());

        return "redirect:/home";
    }

    @PostMapping("/delete_certificate/{id}")
    public String deleteCertificate(@PathVariable("id") Long id)
    {
        Optional<Certificate> existingCertificate = certificateService.findById(id);
        Student student = existingCertificate.get().getStudent();
        studentService.decreaseRating(student.getId());
        log.info("Student rating " + student.getUser().getUsername() + " decreased");

        existingCertificate.ifPresent(
                certificate -> certificateService.deleteById(certificate.getId())
        );
        log.info("Student " + student.getUser().getUsername() + " delete certificate with id: " + id);

        return "redirect:/home";
    }
}