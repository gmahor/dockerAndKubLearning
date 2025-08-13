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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController{

    private final UserService userService;
    private final ResponseHandler responseHandler;

    @PostMapping(GlobalUrl.ADD_USER)
    public ResponseEntity<Object> addUser(@RequestBody AddUserDTO addUserDTO){
        DataContainer data = new DataContainer();
        try{
            data =  userService.addUser(addUserDTO);
        } catch (Exception e) {
            log.error(GlobalConstant.ERROR_AT.getMessage(), GlobalUrl.ADD_USER, e.toString());
            data.setMsg(e.getMessage());
            return responseHandler.response("", data.getMsg(), true, HttpStatus.BAD_REQUEST);
        }
        boolean isSuccess = null != data.getMsgToCheck() && data.getMsgToCheck().equals(GlobalConstant.MSG_SUCCESS.getMessage());
        return responseHandler.response(isSuccess ? data.getData() : "", data.getMsg(), isSuccess, HttpStatus.OK);
    }

    @PutMapping(GlobalUrl.UPDATE_USER)
    public ResponseEntity<Object> updateUser(@RequestBody AddUserDTO addUserDTO){
        DataContainer data = new DataContainer();
        try {
            data = userService.updateUser(addUserDTO);
        } catch (Exception e) {
            log.error(GlobalConstant.ERROR_AT.getMessage(), GlobalUrl.UPDATE_USER, e.toString());
            data.setMsg(GlobalConstant.MSG_ERROR.getMessage());
            return responseHandler.response("", data.getMsg(), true, HttpStatus.BAD_REQUEST);
        }
        boolean isSuccess = null != data.getMsgToCheck() && data.getMsgToCheck().equals(GlobalConstant.MSG_SUCCESS.getMessage());
        return responseHandler.response(isSuccess ? data.getData() : "", data.getMsg(), isSuccess, HttpStatus.OK);
    }

    @GetMapping(GlobalUrl.GET_USER_BY_ID+"/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id){
        DataContainer data = new DataContainer();
        try {
            data = userService.getUser(id);
        }catch (Exception e) {
            log.error(GlobalConstant.ERROR_AT.getMessage(), GlobalUrl.GET_USER_BY_ID, e.toString());
            data.setMsg(GlobalConstant.MSG_ERROR.getMessage());
            return responseHandler.response("", data.getMsg(), true, HttpStatus.BAD_REQUEST);
        }
        boolean isSuccess = null != data.getMsgToCheck() && data.getMsgToCheck().equals(GlobalConstant.MSG_SUCCESS.getMessage());
        return responseHandler.response(isSuccess ? data.getData() : "", data.getMsg(), isSuccess, HttpStatus.OK);
    }

    @DeleteMapping(GlobalUrl.DELETE_USER_BY_ID+"/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id){
        DataContainer data = new DataContainer();
        try {
            data = userService.delete(id);
        }catch (Exception e) {
            log.error(GlobalConstant.ERROR_AT.getMessage(), GlobalUrl.DELETE_USER_BY_ID, e.toString());
            data.setMsg(GlobalConstant.MSG_ERROR.getMessage());
            return responseHandler.response("", data.getMsg(), true, HttpStatus.BAD_REQUEST);
        }
        boolean isSuccess = null != data.getMsgToCheck() && data.getMsgToCheck().equals(GlobalConstant.MSG_SUCCESS.getMessage());
        return responseHandler.response(isSuccess ? data.getData() : "", data.getMsg(), isSuccess, HttpStatus.OK);
    }

}
