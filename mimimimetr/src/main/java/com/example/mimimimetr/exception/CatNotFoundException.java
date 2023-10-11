package com.example.mimimimetr.exception;

public class CatNotFoundException extends RuntimeException {
    public CatNotFoundException(String message) {
        super(message);
    }
}
