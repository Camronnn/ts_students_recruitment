package com.amirdigiev.tsaritsynostudentportfolio.model.role;

import com.amirdigiev.tsaritsynostudentportfolio.model.Certificate;
import com.amirdigiev.tsaritsynostudentportfolio.model.Event;
import com.amirdigiev.tsaritsynostudentportfolio.model.RoleEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "student", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupNumber;
    private String faculty;
    private Integer rating;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Certificate> certificates;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events = new HashSet<>();

    public Student(String faculty,
                   String groupNumber,
                   List<Certificate> certificates,
                   User user,
                   Set<Event> events) {
        this.faculty = faculty;
        this.rating = 0;
        this.groupNumber = groupNumber;
        this.certificates = certificates;
        this.user = user;
        this.events = events;
        this.user.setRole(RoleEnum.ROLE_STUDENT.getTypeRole());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(groupNumber, student.groupNumber) &&
                Objects.equals(faculty, student.faculty) &&
                Objects.equals(rating, student.rating) &&
                Objects.equals(user, student.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                groupNumber,
                faculty,
                rating,
                user
        );
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", groupNumber='" + groupNumber + '\'' +
                ", faculty='" + faculty + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                '}';
    }
}
