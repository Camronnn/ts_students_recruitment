//package com.amirdigiev.tsaritsynostudentportfolio.service;
//
//import com.amirdigiev.tsaritsynostudentportfolio.model.Director;
//import com.amirdigiev.tsaritsynostudentportfolio.model.Role;
//import com.amirdigiev.tsaritsynostudentportfolio.repository.DirectorRepository;
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
//import java.util.HashSet;
//import java.util.Set;
//
//@Service("directorService")
//@SuppressWarnings("Duplicates")
//public class DirectorService implements UserDetailsService {
//
//    private final DirectorRepository directorRepository;
//
//    @Autowired
//    public DirectorService(DirectorRepository directorRepository) {
//        this.directorRepository = directorRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Director director = directorRepository.findByUsername(username);
//
//        if (director != null) {
//            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//            for(Role role : director.getRoles()) {
//                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//            }
//
//            return new User(director.getUsername(), director.getPassword(), director.getRoles());
//        }
//
//        return null;
//    }
//
//    public Director add(Director director) {
//        return directorRepository.save(director);
//    }
//
//    public void deleteById(Long id) {
//        directorRepository.deleteById(id);
//    }
//
//}
