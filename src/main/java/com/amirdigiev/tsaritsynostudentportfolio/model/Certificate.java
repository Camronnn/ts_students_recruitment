package com.amirdigiev.tsaritsynostudentportfolio.model;


import com.amirdigiev.tsaritsynostudentportfolio.model.role.Student;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "certificate", schema = "public")
@Setter
@Getter
@NoArgsConstructor
public class Certificate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String competency;
    private String certificateImage;
    private boolean approved;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Certificate(String competency,
                       String certificateImage,
                       Student student,
                       boolean approved) {
        this.competency = competency;
        this.certificateImage = certificateImage;
        this.student = student;
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certificate)) return false;
        Certificate that = (Certificate) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(competency, that.competency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, competency);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", name='" + competency + '\'' +
                '}';
    }
}
