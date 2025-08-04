package com.tech.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserDTO {
    private String username;

    private String email;

    private String password;

    private String roleType;
}
