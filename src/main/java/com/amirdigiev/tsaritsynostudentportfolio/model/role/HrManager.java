package com.amirdigiev.tsaritsynostudentportfolio.model.role;

import com.amirdigiev.tsaritsynostudentportfolio.model.RoleEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "hr", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class HrManager implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public HrManager(String company, User user) {
        this.company = company;
        this.user = user;
        this.user.setRole(RoleEnum.ROLE_HRMANAGER.getTypeRole());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HrManager)) return false;
        HrManager hrManager = (HrManager) o;
        return Objects.equals(id, hrManager.id) &&
                Objects.equals(company, hrManager.company) &&
                Objects.equals(user, hrManager.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, user);
    }

    @Override
    public String toString() {
        return "HrManager{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", user=" + user +
                '}';
    }
}
