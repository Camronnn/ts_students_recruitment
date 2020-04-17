package com.amirdigiev.tsaritsynostudentportfolio.service;

import com.amirdigiev.tsaritsynostudentportfolio.model.*;
import com.amirdigiev.tsaritsynostudentportfolio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final FileService fileService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${application.avatar-folder}")
    private String avatarFolder;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       @Lazy FileService fileService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileService = fileService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for(Role role : user.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles());
        }

        throw new UsernameNotFoundException("Пользователя с именем " + username + " не существует!");
    }

    public User getAnAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName());
    }


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void update(String name,
                       String surname,
                       String patronymic,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
                       String number,
                       String hometown,
                       String mail,
                       MultipartFile avatar) throws IOException {
        User currentUser = getAnAuthorizedUser();
        currentUser.setName(name);
        currentUser.setSurname(surname);
        currentUser.setPatronymic(patronymic);
        currentUser.setBirthday(birthday);
        currentUser.setNumber(number);
        currentUser.setHometown(hometown);
        currentUser.setMail(mail);

        String avatarName = fileService.uploadAvatar(avatar, Paths.get(avatarFolder));
        currentUser.setAvatar(avatarName);

        userRepository.save(currentUser);
    }

    public void deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
        }
        else {
            throw new UsernameNotFoundException("Такого пользователя не существует!");
        }
    }

    public void updateAvatarOfCurrentUser(String avatarName) {
        User currentUser = getAnAuthorizedUser();
        currentUser.setAvatar(avatarName);

        userRepository.save(currentUser);
    }

    public void deleteAvatar() {
        updateAvatarOfCurrentUser("default_avatar.png");
    }
}