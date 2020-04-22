package com.amirdigiev.tsaritsynostudentportfolio.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "student", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User implements Serializable {

    private String faculty;
    private Integer rating;

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
                   String avatar,
                   Set<Role> roles,
                   String faculty,
                   Integer rating) {
        super(id, username, password, matchingPassword, name, surname, patronymic, birthday, hometown, number, mail, avatar, roles);
        this.faculty = faculty;
        this.rating = rating;
    }
}
