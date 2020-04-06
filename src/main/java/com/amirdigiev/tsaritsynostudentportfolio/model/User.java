package com.amirdigiev.tsaritsynostudentportfolio.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Transient
    private String matchingPassword;
    private String name;
    private String surname;
    private Integer age;
    private LocalDate birthday;
    private String hometown;
    private String number;
}
