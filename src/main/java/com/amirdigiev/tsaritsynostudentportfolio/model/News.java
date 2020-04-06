package com.amirdigiev.tsaritsynostudentportfolio.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "news", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
}
