package com.amirdigiev.tsaritsynostudentportfolio.component;

import com.amirdigiev.tsaritsynostudentportfolio.model.role.User;
import com.amirdigiev.tsaritsynostudentportfolio.model.dto.SuperUserDto;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("Duplicates")
public class Converter {

    public SuperUserDto toDto(User user) {
        SuperUserDto userDto = new SuperUserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setMatchingPassword(user.getMatchingPassword());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setPatronymic(user.getPatronymic());
        userDto.setBirthday(user.getBirthday());
        userDto.setHometown(user.getHometown());
        userDto.setAvatar(user.getAvatar());
        userDto.setNumber(user.getNumber());
        userDto.setMail(user.getEmail());
        userDto.setRole(user.getRole());

        return userDto;
    }

    public User toEntity(SuperUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setMatchingPassword(userDto.getMatchingPassword());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPatronymic(userDto.getPatronymic());
        user.setBirthday(userDto.getBirthday());
        user.setHometown(userDto.getHometown());
        user.setAvatar(userDto.getAvatar());
        user.setNumber(userDto.getNumber());
        user.setEmail(userDto.getMail());
        user.setRole(userDto.getRole());

        return user;
    }
}
