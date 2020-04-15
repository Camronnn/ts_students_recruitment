package com.amirdigiev.tsaritsynostudentportfolio.service;

import com.amirdigiev.tsaritsynostudentportfolio.model.*;
import com.amirdigiev.tsaritsynostudentportfolio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final CommonRepository<User> commonRepository;
    private final StudentRepository studentRepository;
    private final DirectorRepository directorRepository;
    private final HrManagerRepository hrManagerRepository;
    private final ModeratorRepository moderatorRepository;

    @Autowired
    public UserService(StudentRepository studentRepository,
                       DirectorRepository directorRepository,
                       HrManagerRepository hrManagerRepository,
                       ModeratorRepository moderatorRepository,
                       @Qualifier("studentRepository") CommonRepository<User> commonRepository) {
        this.studentRepository = studentRepository;
        this.directorRepository = directorRepository;
        this.hrManagerRepository = hrManagerRepository;
        this.moderatorRepository = moderatorRepository;
        this.commonRepository = commonRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = commonRepository.findByUsername(username);

        if(user != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for(Role role : user.getRoles()) {
                if(user.getClass() == Student.class) {
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
                }
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles());
        }

        throw new UsernameNotFoundException("Пользователя с именем " + username + " не существует!");
    }

    public Optional<User> findById(Long id) {
        Optional<User> user = commonRepository.findById(id);
        if (user.isPresent()) {
            return user;
        }

        throw new NullPointerException("Пользователя с id: " + id + " не существует!");
    }

    public <U extends User> void checkUserAuthorities(U user) {
        if (user instanceof Student) {
            studentRepository.save(user);
        }
        if (user instanceof Director) {
            directorRepository.save(user);
        }
        if (user instanceof HrManager) {
            hrManagerRepository.save(user);
        }
    }
}