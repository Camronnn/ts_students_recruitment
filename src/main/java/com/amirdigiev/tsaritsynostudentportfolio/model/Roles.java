package com.amirdigiev.tsaritsynostudentportfolio.model;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    STUDENT,
    MODERATOR,
    HRMANAGER,
    DIRECTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
