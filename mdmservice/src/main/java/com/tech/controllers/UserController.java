package com.tech.controllers;

import com.tech.commons.DataContainer;
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
        try {
            String msg = userService.addUser(addUserDTO);
            return responseHandler.successResp("", msg, true, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(GlobalConstant.ERROR_AT.getMessage(), GlobalUrl.ADD_USER, e.toString());
            return responseHandler.errorResp(GlobalUrl.ADD_USER, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(GlobalUrl.UPDATE_USER)
    public ResponseEntity<Object> updateUser(@RequestBody AddUserDTO addUserDTO) {
        log.info("Update User Request Received by dto : {}", addUserDTO);
        DataContainer data = new DataContainer();
        try {
            data = userService.updateUser(addUserDTO);
        } catch (Exception e) {
            log.error(GlobalConstant.ERROR_AT.getMessage(), GlobalUrl.UPDATE_USER, e.toString());
            data.setMsg(GlobalConstant.MSG_ERROR.getMessage());
            return responseHandler.successResp("", data.getMsg(), true, HttpStatus.BAD_REQUEST);
        }
        boolean isSuccess = null != data.getMsgToCheck() && data.getMsgToCheck().equals(GlobalConstant.MSG_SUCCESS.getMessage());
        return responseHandler.errorResp(isSuccess ? data.getData() : "", data.getMsg(), isSuccess, HttpStatus.OK);
    }

    @GetMapping(GlobalUrl.GET_USER_BY_ID + "/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id) {
        log.info("Get User Request Received by id : {}", id);
        DataContainer data = new DataContainer();
        try {
            data = userService.getUser(id);
        } catch (Exception e) {
            log.error(GlobalConstant.ERROR_AT.getMessage(), GlobalUrl.GET_USER_BY_ID, e.toString());
            data.setMsg(GlobalConstant.MSG_ERROR.getMessage());
            return responseHandler.successResp("", data.getMsg(), true, HttpStatus.BAD_REQUEST);
        }
        boolean isSuccess = null != data.getMsgToCheck() && data.getMsgToCheck().equals(GlobalConstant.MSG_SUCCESS.getMessage());
        return responseHandler.errorResp(isSuccess ? data.getData() : "", data.getMsg(), isSuccess, HttpStatus.OK);
    }

    @DeleteMapping(GlobalUrl.DELETE_USER_BY_ID + "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        log.info("Delete User Request Received by id : {}", id);
        DataContainer data = new DataContainer();
        try {
            data = userService.delete(id);
        } catch (Exception e) {
            log.error(GlobalConstant.ERROR_AT.getMessage(), GlobalUrl.DELETE_USER_BY_ID, e.toString());
            data.setMsg(GlobalConstant.MSG_ERROR.getMessage());
            return responseHandler.successResp("", data.getMsg(), true, HttpStatus.BAD_REQUEST);
        }
        boolean isSuccess = null != data.getMsgToCheck() && data.getMsgToCheck().equals(GlobalConstant.MSG_SUCCESS.getMessage());
        return responseHandler.errorResp(isSuccess ? data.getData() : "", data.getMsg(), isSuccess, HttpStatus.OK);
    }

}
