package com.example.quicktask.common;

import lombok.Getter;

@Getter
public enum CommonEnum {

    // Roles
    USER(200, "USER"),
    ADMIN(200, "ADMIN"),

    // Success Responses
    SUCCESS(200, "Success"),
    CREATED(201, "Resource created successfully"),
    UPDATED(200, "Resource updated successfully"),
    DELETED(200, "Resource deleted successfully"),

    // Client Errors
    PARAMETER_MISSING(400, "Required parameter is missing"),
    INVALID_REQUEST(400, "Invalid request"),
    INVALID_CREDENTIALS(401, "Invalid email or password"),
    UNAUTHORIZED(401, "Unauthorized access"),
    FORBIDDEN(403, "Access denied"),

    // Resource Errors
    NO_DATA_FOUND(404, "No data found"),
    USER_NOT_FOUND(404, "User not found"),
    RESOURCE_NOT_FOUND(404, "Requested resource not found"),

    // Conflict Errors
    EMAIL_ALREADY_EXISTS(409, "Email already exists"),
    USER_ALREADY_EXISTS(409, "User already exists"),
    DUPLICATE_RECORD(409, "Record already exists"),

    // Server Errors
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    DATABASE_ERROR(500, "Database operation failed"),
    TOKEN_EXPIRED(401, "Token has expired"),
    INVALID_TOKEN(401, "Invalid token");

    private final Integer key;
    private final String value;

     CommonEnum(Integer key, String value){
         this.key = key;
         this.value = value;
    }

//    public String getValue(){
//        return this.value;
//    }
}
