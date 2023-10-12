package com.crud.basic.springboot.springbootbasiccrud.system.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super("User already exist with email " + email);
    }
}
