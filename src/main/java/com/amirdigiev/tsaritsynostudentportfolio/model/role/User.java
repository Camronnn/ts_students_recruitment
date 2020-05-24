package com.amirdigiev.tsaritsynostudentportfolio.model.role;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr", schema = "public")
@SuppressWarnings("Duplicates")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters")
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull(message = "Password cannot be null")
//    @Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
    @Column(nullable = false)
    private String password;

    @Transient
    private String matchingPassword;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 32, message = "Name must be between 3 and 32 characters")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Surname cannot be null")
    @Size(min = 3, max = 32, message = "Surname must be between 3 and 32 characters")
    @Column(nullable = false)
    private String surname;

    @Size(min = 3, max = 32, message = "Patronymic must be between 3 and 32 characters")
    private String patronymic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of birth cannot be null")
    @Column(nullable = false)
    private LocalDate birthday;

    @Size(min = 3, max = 32, message = "Hometown must be between 3 and 32 characters")
    private String hometown;

    @Size(min = 11, max = 16, message = "Number must be between 11 and 16 characters")
    private String number;

    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;
    private String avatar;

    @NotNull(message = "Role cannot be null")
    @Column(nullable = false)
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(matchingPassword, user.matchingPassword) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(patronymic, user.patronymic) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(hometown, user.hometown) &&
                Objects.equals(number, user.number) &&
                Objects.equals(email, user.email) &&
                Objects.equals(avatar, user.avatar) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, username,
                password, matchingPassword,
                name, surname,
                patronymic, birthday,
                hometown, number,
                email, avatar,
                role);
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", hometown='" + hometown + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
