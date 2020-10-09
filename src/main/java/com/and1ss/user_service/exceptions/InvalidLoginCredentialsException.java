package com.and1ss.user_service.exceptions;

public class InvalidLoginCredentialsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Incorrect login or password";
    }
}
