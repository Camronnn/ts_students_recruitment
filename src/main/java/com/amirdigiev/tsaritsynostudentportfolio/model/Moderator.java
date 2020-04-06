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
@Entity(name = "Moderator")
@Table(name = "moderator", schema = "public")
public class Moderator extends User {

    @Builder
    public Moderator(Long id,
                     String username,
                     String password,
                     String matchingPassword,
                     String name,
                     String surname,
                     Integer age,
                     LocalDate birthday,
                     String hometown,
                     String number) {
        super(id, username, password, matchingPassword, name, surname, age, birthday, hometown, number);
    }
}