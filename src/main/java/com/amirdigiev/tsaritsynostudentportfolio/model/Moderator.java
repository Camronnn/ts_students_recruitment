package com.amirdigiev.tsaritsynostudentportfolio.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "moderator", schema = "public")
public class Moderator extends User {

    @Builder
    public Moderator(Long id,
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
                     List<Role> roles) {
        super(id,
                username,
                password,
                matchingPassword,
                name,
                surname,
                patronymic,
                birthday,
                hometown,
                number,
                mail,
                roles);
    }
}