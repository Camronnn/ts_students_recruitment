package com.amirdigiev.tsaritsynostudentportfolio.repostitory;

import com.amirdigiev.tsaritsynostudentportfolio.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s")
    List<Student> getAllStudents();

    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Student getStudentById(@Param("id") Long id);
}
