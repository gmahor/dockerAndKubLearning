package com.tech.controllers;

import com.tech.commons.GlobalConstant;
import com.tech.commons.GlobalUrl;
import com.tech.commons.ResponseHandler;
import com.tech.dtos.AddUserDTO;
import com.tech.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/users",
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private final UserService userService;
    private final ResponseHandler responseHandler;

    @PostMapping(GlobalUrl.ADD_USER)
    public ResponseEntity<Object> addUser(@RequestBody AddUserDTO addUserDTO) {
        log.info("Add User Request Received by dto : {}", addUserDTO);
        return responseHandler.successResp("", userService.addUser(addUserDTO), true, HttpStatus.CREATED);

    }

    @PutMapping(GlobalUrl.UPDATE_USER)
    public ResponseEntity<Object> updateUser(@RequestBody AddUserDTO addUserDTO) {
        log.info("Update User Request Received by dto : {}", addUserDTO);
        return responseHandler.successResp("", userService.updateUser(addUserDTO), true, HttpStatus.OK);

    }

    @GetMapping(GlobalUrl.GET_USER_BY_ID + "/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id) {
        log.info("Get User Request Received by id : {}", id);
        return responseHandler.successResp(userService.getUser(id), GlobalConstant.MSG_SUCCESS.getMessage(), true, HttpStatus.OK);
    }

    @DeleteMapping(GlobalUrl.DELETE_USER_BY_ID + "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        log.info("Delete User Request Received by id : {}", id);
        return responseHandler.successResp("", userService.delete(id), true, HttpStatus.OK);
    }

}
