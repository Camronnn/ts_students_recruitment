package com.amirdigiev.tsaritsynostudentportfolio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum RoleEnum {
    ROLE_STUDENT("STUDENT"),
    ROLE_DIRECTOR("DIRECTOR"),
    ROLE_HRMANAGER("MANAGER"),
    ROLE_ADMIN("ADMIN");

    private String typeRole;

    @Override
    public String toString() {
        return typeRole;
    }
}
