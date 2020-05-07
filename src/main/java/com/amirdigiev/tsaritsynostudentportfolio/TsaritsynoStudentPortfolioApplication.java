package com.amirdigiev.tsaritsynostudentportfolio;

import com.amirdigiev.tsaritsynostudentportfolio.dao.admin.AdminService;
import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.Admin;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TsaritsynoStudentPortfolioApplication {
	public static void main(String[] args) {
		SpringApplication.run(TsaritsynoStudentPortfolioApplication.class, args);
	}
}
