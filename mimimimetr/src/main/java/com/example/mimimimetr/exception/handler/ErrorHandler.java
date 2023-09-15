package com.example.mimimimetr.exception.handler;

import com.example.mimimimetr.exception.UniqueUsernameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.mimimimetr.controller")
public class ErrorHandler {

    @ExceptionHandler(UniqueUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public String handleUniqueEmailException(UniqueUsernameException e) {
        return e.getMessage();
    }
}
