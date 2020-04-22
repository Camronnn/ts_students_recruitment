//package com.amirdigiev.tsaritsynostudentportfolio.validator;
//
//import com.amirdigiev.tsaritsynostudentportfolio.model.User;
//import com.amirdigiev.tsaritsynostudentportfolio.service.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//
//@Component
//public class UserValidator implements Validator {
//
//    private final UserRepository userService;
//
//    @Autowired
//    public UserValidator(UserRepository userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return User.class.equals(aClass);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//
//    }
//}
