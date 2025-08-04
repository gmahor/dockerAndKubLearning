package com.tech.controllers;

import com.tech.dtos.AddUserDTO;
import com.tech.enities.Users;
import com.tech.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody AddUserDTO addUserDTO){
        try{
            String result =  userService.addUser(addUserDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity<String> updateUser(@RequestBody AddUserDTO addUserDTO){
        try {
            String result = userService.updateUser(addUserDTO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-user-by-id")
    public ResponseEntity<Users> getUser(@PathVariable Long id){
        try {
            Users user = userService.getUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete-user-by-id")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try {
            String result = userService.delete(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
