package com.amirdigiev.tsaritsynostudentportfolio.model.role;

import com.amirdigiev.tsaritsynostudentportfolio.model.RoleEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "director", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class Director implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String department;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Director(String department, User user) {
        this.department = department;
        this.user = user;
        this.user.setRole(RoleEnum.ROLE_DIRECTOR.getTypeRole());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Director)) return false;
        Director director = (Director) o;
        return Objects.equals(id, director.id) &&
                Objects.equals(department, director.department) &&
                Objects.equals(user, director.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department, user);
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", user=" + user +
                '}';
    }
}
