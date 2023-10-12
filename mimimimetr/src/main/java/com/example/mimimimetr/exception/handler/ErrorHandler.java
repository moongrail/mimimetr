package com.example.mimimimetr.exception.handler;

import com.example.mimimimetr.exception.EntityNotFoundException;
import com.example.mimimimetr.exception.UniqueEmailCatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.mimimimetr")
public class ErrorHandler {

    @ExceptionHandler(UniqueEmailCatException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public String handleUniqueEmailException(UniqueEmailCatException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleCatNotFoundException(EntityNotFoundException e) {
        return e.getMessage();
    }
}
