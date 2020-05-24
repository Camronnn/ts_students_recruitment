package com.amirdigiev.tsaritsynostudentportfolio.component;

import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailSender {

    private final JavaMailSender emailSender;
    private final UserService userService;

    @Autowired
    public EmailSender(JavaMailSender emailSender,
                       UserService userService)
    {
        this.emailSender = emailSender;
        this.userService = userService;
    }

    public void sendSimpleMessage(String subject,
                                  String text)
    {
        List<User> students = userService.findAll().stream()
                .filter(user -> user.getRole().equals("STUDENT"))
                .collect(Collectors.toList());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setTo("amir.digiev@gmail.com");
        emailSender.send(message);
    }
}
