package com.tech.services;

import com.tech.dtos.AddUserDTO;
import com.tech.enities.Users;
import com.tech.enums.RoleType;
import com.tech.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;

    //add
    public String addUser(AddUserDTO addUserDTO) {
            String username = addUserDTO.getUsername();

            Optional<Users> userOpt = userRepo.findByUsername(username);

            if (userOpt.isPresent()) {
                throw new RuntimeException("User already present with this username");
            }

            Users user = new Users();
            user.setEmail(addUserDTO.getEmail());
            user.setUsername(addUserDTO.getUsername());
            user.setPassword(addUserDTO.getPassword());
            RoleType role = RoleType.valueOf(addUserDTO.getRoleType());
            user.setRoleType(role);
            userRepo.save(user);
            return "User created successfully";
    }

    //update
    public String updateUser(AddUserDTO addUserDTO) {
        String username = addUserDTO.getUsername();
        Users user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(addUserDTO.getEmail());
        user.setUsername(addUserDTO.getUsername());
        user.setPassword(addUserDTO.getPassword());
        RoleType role = RoleType.valueOf(addUserDTO.getRoleType());
        user.setRoleType(role);
        userRepo.save(user);
        return "User updated successfully!";
    }


    //get
    public Users getUser(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    //delete
    public String delete(Long userId) {
      Users user =   userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

      userRepo.deleteById(user.getId());
      return "User deleted successfully";
    }

}
