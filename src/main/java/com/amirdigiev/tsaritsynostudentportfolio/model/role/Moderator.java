package com.amirdigiev.tsaritsynostudentportfolio.model.role;

import com.amirdigiev.tsaritsynostudentportfolio.model.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "moderator", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class Moderator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Moderator(User user) {
        this.user = user;
        this.user.setRole(RoleEnum.ROLE_MODERATOR.getTypeRole());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Moderator)) return false;
        Moderator moderator = (Moderator) o;
        return Objects.equals(id, moderator.id) &&
                Objects.equals(user, moderator.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }

    @Override
    public String toString() {
        return "Moderator{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
