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
@Entity(name = "HrManager")
@Table(name = "hr_manager", schema = "public")
public class HrManager extends User {

    private String company;

    @Builder
    public HrManager(Long id,
                     String username,
                     String password,
                     String matchingPassword,
                     String name,
                     String surname,
                     Integer age,
                     LocalDate birthday,
                     String hometown,
                     String number,
                     String company) {
        super(id, username, password, matchingPassword, name, surname, age, birthday, hometown, number);
        this.company = company;
    }
}
