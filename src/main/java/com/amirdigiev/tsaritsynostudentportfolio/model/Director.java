package com.amirdigiev.tsaritsynostudentportfolio.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "director", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Director extends User implements Serializable {

    private String department;

    @Builder
    public Director(Long id,
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
                    String department) {
        super(id, username, password, matchingPassword, name, surname, patronymic, birthday, hometown, number, mail, avatar, roles);
        this.department = department;
    }
}
