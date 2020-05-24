package com.amirdigiev.tsaritsynostudentportfolio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("Duplicates")
public class SuperUserDto implements Serializable {

    private String username;
    private String password;
    private String matchingPassword;
    private String name;
    private String surname;
    private String patronymic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String hometown;
    private String number;
    private String mail;
    private String avatar;
    private String role;

    private String department;
    private String company;
    private String groupNumber;
    private String faculty;

    @Override
    public String toString() {
        return "SuperUserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", hometown='" + hometown + '\'' +
                ", number='" + number + '\'' +
                ", email='" + mail + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role='" + role + '\'' +
                ", department='" + department + '\'' +
                ", company='" + company + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
