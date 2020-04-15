package com.amirdigiev.tsaritsynostudentportfolio.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "student", schema = "public")
public class Student extends User {

    private String studentIdNumber;
    private String groupNumber;
    private String academicPerformance;
    private String faculty;
    private String department;
    private long rating;

    @Builder
    public Student(Long id,
                   String username,
                   String password,
                   String matchingPassword,
                   String name,
                   String surname,
                   String patronymic,
                   LocalDate birthday,
                   String hometown,
                   String number,
                   String mail,
                   String studentIdNumber,
                   String groupNumber,
                   String academicPerformance,
                   String faculty,
                   String department,
                   List<Role> roles) {
        super(id, username, password, matchingPassword, name, patronymic, surname, birthday, hometown, number, mail, roles);
        this.studentIdNumber = studentIdNumber;
        this.groupNumber = groupNumber;
        this.academicPerformance = academicPerformance;
        this.faculty = faculty;
        this.department = department;
        this.rating = 0;
    }
}