package com.tech.services;

import com.tech.commons.DataContainer;
import com.tech.commons.GlobalConstant;
import com.tech.dtos.AddUserDTO;
import com.tech.enities.Users;
import com.tech.enums.RoleType;
import com.tech.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;

    //add
    public DataContainer addUser(AddUserDTO addUserDTO) {
        DataContainer data = new DataContainer();

        if (!isValidRole(addUserDTO.getRoleType())) {
            throw new IllegalArgumentException("Invalid Role: " + addUserDTO.getRoleType());
        }

        userRepo.findByUsername(addUserDTO.getUsername())
                .ifPresent(u -> {
                    throw new IllegalStateException("User already exists with username: " + addUserDTO.getUsername());
                });

        Users user = new Users();
        user.setEmail(addUserDTO.getEmail());
        user.setUsername(addUserDTO.getUsername());
        user.setPassword(addUserDTO.getPassword());
        user.setRoleType(RoleType.valueOf(addUserDTO.getRoleType()));
        userRepo.save(user);
        data.setMsg(GlobalConstant.USER_ADDED_SUCCESS.getMessage());
        data.setMsgToCheck(GlobalConstant.MSG_SUCCESS.getMessage());
        return data;
    }

    //update
    public DataContainer updateUser(AddUserDTO addUserDTO) {
        DataContainer data = new DataContainer();
        String username = addUserDTO.getUsername();

        if (!isValidRole(addUserDTO.getRoleType())) {
            throw new IllegalArgumentException("Invalid Role: " + addUserDTO.getRoleType());
        }

        Users user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(addUserDTO.getEmail());
        user.setUsername(addUserDTO.getUsername());
        user.setPassword(addUserDTO.getPassword());
        RoleType role = RoleType.valueOf(addUserDTO.getRoleType());
        user.setRoleType(role);
        userRepo.save(user);
        data.setMsg(GlobalConstant.USER_UPDATE_SUCCESS.getMessage());
        data.setMsgToCheck(GlobalConstant.MSG_SUCCESS.getMessage());
        return data;
    }


    //get
    public DataContainer getUser(Long userId) {
        DataContainer data = new DataContainer();
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException(GlobalConstant.USER_NOT_FOUNT.getMessage()));
        data.setData(user);
        data.setMsg(GlobalConstant.MSG_SUCCESS.getMessage());
        data.setMsgToCheck(GlobalConstant.MSG_SUCCESS.getMessage());
        return data;
    }

    //delete
    public DataContainer delete(Long userId) {
        DataContainer data = new DataContainer();
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException(GlobalConstant.USER_NOT_FOUNT.getMessage()));
        userRepo.deleteById(user.getId());
        data.setMsgToCheck(GlobalConstant.MSG_SUCCESS.getMessage());
        data.setMsg(GlobalConstant.USER_DELETED_SUCCESS.getMessage());
        return data;
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
