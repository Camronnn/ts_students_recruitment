package com.amirdigiev.tsaritsynostudentportfolio.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity(name = "Director")
@Table(name = "director", schema = "public")
public class Director extends User {

    private String department;

    @Builder
    public Director(Long id,
                   String username,
                   String password,
                   String matchingPassword,
                   String name,
                   String surname,
                   Integer age,
                   LocalDate birthday,
                   String hometown,
                   String number,
                   String department) {
        super(id, username, password, matchingPassword, name, surname, age, birthday, hometown, number);
        this.department = department;
    }
}
