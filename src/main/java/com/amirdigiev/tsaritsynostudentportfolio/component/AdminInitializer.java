package com.amirdigiev.tsaritsynostudentportfolio.component;

import com.amirdigiev.tsaritsynostudentportfolio.dao.admin.AdminService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.Admin;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class AdminInitializer {

    private final UserService userService;
    private final AdminService adminService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminInitializer(UserService userService,
                            AdminService adminService,
                            BCryptPasswordEncoder passwordEncoder)
    {
        this.userService = userService;
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        List<User> users = userService.findAll();
        boolean exist = false;

        for(User user : users) {
            if ("Admin".equals(user.getUsername())) {
                exist = true;
            }
        }

        User newUser = null;
        if (!exist) {
            newUser = new User();
            newUser.setUsername("Admin");
            newUser.setPassword("12345");
            newUser.setRole("ADMIN");
            userService.add(newUser);
        }

        if (newUser != null) {
            Admin admin = new Admin();
            admin.setUser(newUser);
            adminService.add(admin);
        }
    }
}
