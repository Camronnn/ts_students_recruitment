package com.amirdigiev.tsaritsynostudentportfolio.model;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ROLE_STUDENT,
    ROLE_MODERATOR,
    ROLE_HRMANAGER,
    ROLE_DIRECTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
