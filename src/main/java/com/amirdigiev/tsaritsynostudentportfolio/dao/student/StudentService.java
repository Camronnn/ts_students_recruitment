package com.amirdigiev.tsaritsynostudentportfolio.dao.student;


import com.amirdigiev.tsaritsynostudentportfolio.dao.user.UserService;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.Student;
import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("studentService")
@SuppressWarnings("Duplicates")
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserService userService;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          @Lazy UserService userService)
    {
        this.studentRepository = studentRepository;
        this.userService = userService;
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public void update(Student updateStudent) {
        User currentUser = userService.getAnAuthorizedUser();

        List<Student> students = findAll();
        for (Student student : students) {
            if (student.getUser().getId().equals(currentUser.getId())) {
                student.setFaculty(updateStudent.getFaculty());
                student.setGroupNumber(updateStudent.getGroupNumber());
                studentRepository.save(student);
                break;
            }
        }
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public void accrueRatingById(Long id, Integer rating) {
        Optional<Student> studentById = studentRepository.findById(id);
        studentById.ifPresent(student ->
                studentById.get().setRating(studentById.get().getRating() + rating)
        );

        studentRepository.save(studentById.get());
    }

    public void decreaseRating(Long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.get().getRating() != 0) {
            existingStudent.ifPresent(student -> student.setRating(student.getRating() - 5));
        }
        studentRepository.save(existingStudent.get());
    }
}
