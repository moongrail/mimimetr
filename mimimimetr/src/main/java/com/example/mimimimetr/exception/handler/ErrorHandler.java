package com.example.mimimimetr.exception.handler;

import com.example.mimimimetr.exception.CatNotFoundException;
import com.example.mimimimetr.exception.UniqueUsernameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.mimimimetr")
public class ErrorHandler {

    @ExceptionHandler(UniqueUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public String handleUniqueEmailException(UniqueUsernameException e) {
        return e.getMessage();
    }

    @ExceptionHandler(CatNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleCatNotFoundException(CatNotFoundException e) {
        return e.getMessage();
    }
}
