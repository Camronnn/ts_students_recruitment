//package com.amirdigiev.tsaritsynostudentportfolio.service;
//
//
//import com.amirdigiev.tsaritsynostudentportfolio.model.Director;
//import com.amirdigiev.tsaritsynostudentportfolio.model.Role;
//import com.amirdigiev.tsaritsynostudentportfolio.model.Student;
//import com.amirdigiev.tsaritsynostudentportfolio.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//import java.util.HashSet;
//import java.util.Set;
//
//@Service("studentService")
//@SuppressWarnings("Duplicates")
//public class StudentService implements UserDetailsService {
//
//    private final StudentRepository studentRepository;
//
//    @Autowired
//    public StudentService(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Student student = studentRepository.findByUsername(username);
//
//        if (student != null) {
//            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//            for(Role role : student.getRoles()) {
//                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//            }
//
//            return new User(student.getUsername(), student.getPassword(), student.getRoles());
//        }
//
//        return null;
//    }
//
//    public Student add(Student student) {
//        return studentRepository.save(student);
//    }
//
//    public void deleteById(Long id) {
//        studentRepository.deleteById(id);
//    }
//}
