package com.amirdigiev.tsaritsynostudentportfolio.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
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
                     String patronymic,
                     LocalDate birthday,
                     String hometown,
                     String number,
                     String mail,
                     String company,
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
        this.company = company;
    }
}
