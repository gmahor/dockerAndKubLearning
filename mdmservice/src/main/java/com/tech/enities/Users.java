package com.tech.enities;

import com.tech.enums.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users extends BaseEntity{

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
