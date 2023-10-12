package com.crud.basic.springboot.springbootbasiccrud.system.exception;

import com.crud.basic.springboot.springbootbasiccrud.system.Result;
import com.crud.basic.springboot.springbootbasiccrud.system.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(UserAlreadyExistException.class)
    Result handleUserNotFoundException(UserAlreadyExistException ex) {
        return new Result(false, StatusCode.FORBIDDEN, ex.getMessage());
    }
}
