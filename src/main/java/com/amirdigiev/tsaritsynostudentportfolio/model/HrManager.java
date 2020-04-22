package com.amirdigiev.tsaritsynostudentportfolio.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "hr", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class HrManager extends User implements Serializable {

    private String company;

    @Builder
    public HrManager(Long id, String username, String password, String matchingPassword, String name, String surname, String patronymic, LocalDate birthday, String hometown, String number, String mail, String avatar, Set<Role> roles, String company) {
        super(id, username, password, matchingPassword, name, surname, patronymic, birthday, hometown, number, mail, avatar, roles);
        this.company = company;
    }
}
