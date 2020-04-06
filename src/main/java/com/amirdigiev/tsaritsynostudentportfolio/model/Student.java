package com.amirdigiev.tsaritsynostudentportfolio.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity(name = "Student")
@Table(name = "student", schema = "public")
public class Student extends User implements Serializable {

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
                   Integer age,
                   LocalDate birthday,
                   String hometown,
                   String number,
                   String studentIdNumber,
                   String groupNumber,
                   String academicPerformance,
                   String faculty,
                   String department) {
        super(id, username, password, matchingPassword, name, surname, age, birthday, hometown, number);
        this.studentIdNumber = studentIdNumber;
        this.groupNumber = groupNumber;
        this.academicPerformance = academicPerformance;
        this.faculty = faculty;
        this.department = department;
        this.rating = 0;
    }
}