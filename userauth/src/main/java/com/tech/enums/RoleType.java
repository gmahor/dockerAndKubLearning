package com.tech.enums;

import java.util.Arrays;
import java.util.List;

public enum RoleType {
    ROLE_USER,
    ROLE_ADMIN;

    public static List<RoleType> getRoles() {
        return Arrays.asList(RoleType.values());
    }
}
