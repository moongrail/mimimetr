package com.example.mimimimetr.exception;

public class UniqueUsernameException extends RuntimeException{
    public UniqueUsernameException(String message) {
        super(message);
    }
}
