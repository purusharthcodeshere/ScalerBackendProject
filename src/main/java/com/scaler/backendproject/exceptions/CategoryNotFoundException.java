package com.scaler.backendproject.exceptions;

public class CategoryNotFoundException extends Exception {
    //Create an object of CategoryNotFoundException Class
    //And set the error method
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
