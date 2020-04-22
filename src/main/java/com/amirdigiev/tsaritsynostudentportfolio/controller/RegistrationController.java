//package com.amirdigiev.tsaritsynostudentportfolio.controller;
//
//import com.amirdigiev.tsaritsynostudentportfolio.model.Director;
//import com.amirdigiev.tsaritsynostudentportfolio.model.HrManager;
//import com.amirdigiev.tsaritsynostudentportfolio.model.Student;
//import com.amirdigiev.tsaritsynostudentportfolio.service.DirectorService;
//import com.amirdigiev.tsaritsynostudentportfolio.service.StudentService;
//import com.amirdigiev.tsaritsynostudentportfolio.service.security.SecurityService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//
//@Slf4j
//@Controller
//@SuppressWarnings("Duplicates")
//public class RegistrationController {
//
//    private final StudentService studentService;
//    private final DirectorService directorService;
//    private final SecurityService securityService;
//
//    @Autowired
//    public RegistrationController(SecurityService securityService,
//                                  StudentService studentService,
//                                  DirectorService directorService) {
//        this.securityService = securityService;
//        this.studentService = studentService;
//        this.directorService = directorService;
//    }
//
//    @GetMapping("/login/student")
//    public String getStudentLoginPage() {
//        return "/login/student_login";
//    }
//
//    @GetMapping("/login/director")
//    public String getDirectorLoginPage() {
//        return "/login/director_login";
//    }
//
//    @GetMapping("/registration/student")
//    public String getStudentRegistrationForm(Model model) {
//        log.info("Registration of a new student");
//        model.addAttribute("newStudent", new Student());
//
//        return "registration/student_registration";
//    }
//
//    @PostMapping("/registration/student")
//    public String registerStudentAccount(@ModelAttribute("newStudent") @Valid Student newStudent,
//                                      BindingResult bindingResult,
//                                      Model model) {
//        log.info("New student: " + newStudent.toString() + " verification");
//        if(bindingResult.hasErrors()) {
//            log.error("Validation errors: " + bindingResult.hasErrors());
//            return "registration/student_registration";
//        }
//
//        if(!newStudent.getPassword().equals(newStudent.getMatchingPassword())) {
//            log.error("Пароли не совпадают: " + newStudent.getPassword() + " != " + newStudent.getMatchingPassword());
//            model.addAttribute("passwordError", "Пароли не совпадают!");
//            return "registration/student_registration";
//        }
//
//        studentService.add(newStudent);
//        securityService.autoLogin(newStudent.getUsername(), newStudent.getMatchingPassword());
//        log.info("New student registered: " + newStudent.toString());
//
//        return "redirect:/login";
//    }
//
//    @GetMapping("/registration/director")
//    public String getDirectorRegistrationForm(Model model) {
//        log.info("Registration of a new director");
//        model.addAttribute("newDirector", new Director());
//
//        return "registration/director_registration";
//    }
//
//    @PostMapping("/registration/director")
//    public String registerDirectorAccount(@ModelAttribute("newDirector") @Valid Director newDirector,
//                                      BindingResult bindingResult,
//                                      Model model) {
//        log.info("New director: " + newDirector.toString() + " verification");
//        if(bindingResult.hasErrors()) {
//            log.error("Validation errors: " + bindingResult.hasErrors());
//            return "registration/director_registration";
//        }
//
//        if(!newDirector.getPassword().equals(newDirector.getMatchingPassword())) {
//            log.error("Пароли не совпадают: " + newDirector.getPassword() + " != " + newDirector.getMatchingPassword());
//            model.addAttribute("passwordError", "Пароли не совпадают!");
//            return "registration/director_registration";
//        }
//
//        directorService.add(newDirector);
//        securityService.autoLogin(newDirector.getUsername(), newDirector.getMatchingPassword());
//        log.info("New director registered: " + newDirector.toString());
//
//        return "redirect:/login";
//    }
//
//    @GetMapping("/registration/manager")
//    public String getManagerRegistrationForm(Model model) {
//        log.info("Registration of a new hr-manager");
//        model.addAttribute("newHrManager", new HrManager());
//
//        return "registration/manager_registration";
//    }
//
////    @PostMapping("/registration/manager")
////    public String registerHrManagerAccount(@ModelAttribute("newHrManager") @Valid HrManager newHrManager,
////                                           BindingResult  bindingResult,
////                                           Model model) {
////        log.info("New hr-manager: " + newHrManager.toString() + " verification");
////        if(bindingResult.hasErrors()) {
////            log.error("Validation errors: " + bindingResult.hasErrors());
////            return "registration/manager";
////        }
////
////        if(!newHrManager.getPassword().equals(newHrManager.getMatchingPassword())) {
////            log.error("Пароли не совпадают: " + newHrManager.getPassword() + " != " + newHrManager.getMatchingPassword());
////            model.addAttribute("passwordError", "Пароли не совпадают!");
////            return "registration/manager";
////        }
////
////        hrManagerService.add(newHrManager);
////        securityService.autoLogin(newHrManager.getUsername(), newHrManager.getMatchingPassword());
////        log.info("New hr-manager registered: " + newHrManager.toString());
////
////        return "redirect:/login";
////    }
//}