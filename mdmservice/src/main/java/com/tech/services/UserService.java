package com.tech.services;

import com.tech.commons.GlobalConstant;
import com.tech.dtos.AddUserDTO;
import com.tech.enities.Users;
import com.tech.enums.RoleType;
import com.tech.exception.AlreadyFoundException;
import com.tech.exception.ResourceNotFoundException;
import com.tech.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;

    //add
    public String addUser(AddUserDTO addUserDTO) {
        if (!isValidRole(addUserDTO.getRoleType())) {
            throw new ResourceNotFoundException("Role", "Role Type", addUserDTO.getRoleType());
        }

        userRepo.findByUsername(addUserDTO.getUsername())
                .ifPresent(u -> {
                    throw new AlreadyFoundException("User","username",u.getUsername());
                });

        Users user = new Users();
        user.setEmail(addUserDTO.getEmail());
        user.setUsername(addUserDTO.getUsername());
        user.setPassword(addUserDTO.getPassword());
        user.setRoleType(RoleType.valueOf(addUserDTO.getRoleType()));
        userRepo.save(user);
        return GlobalConstant.USER_ADDED_SUCCESS.getMessage();
    }

    //update
    public String updateUser(AddUserDTO addUserDTO) {
        String username = addUserDTO.getUsername();

        if (!isValidRole(addUserDTO.getRoleType())) {
            throw new ResourceNotFoundException("Role", "Role Type", addUserDTO.getRoleType());
        }

        Users user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Username", username));
        user.setEmail(addUserDTO.getEmail());
        user.setUsername(addUserDTO.getUsername());
        user.setPassword(addUserDTO.getPassword());
        RoleType role = RoleType.valueOf(addUserDTO.getRoleType());
        user.setRoleType(role);
        userRepo.save(user);
        return GlobalConstant.USER_UPDATE_SUCCESS.getMessage();
    }

    //get
    public Users getUser(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId.toString()));
    }

    //delete
    public String delete(Long userId) {
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId.toString()));
        userRepo.deleteById(user.getId());
        return GlobalConstant.USER_DELETED_SUCCESS.getMessage();
    }

    private boolean isValidRole(String role) {
        if (role == null) return false;
        try {
            RoleType.valueOf(role.trim().toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
