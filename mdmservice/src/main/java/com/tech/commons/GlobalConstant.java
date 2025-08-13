package com.tech.commons;

public enum GlobalConstant {


     MSG_ERROR("error"),
     MSG_SUCCESS("success"),
     USER_ADDED_SUCCESS("User added successfully"),
     ERROR_AT("error at "),
     USER_UPDATE_SUCCESS("User updated successfully!"),
     USER_DELETED_SUCCESS("User deleted successfully!"),
     USER_NOT_FOUNT("User not found");

    private final String message;

    GlobalConstant(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
