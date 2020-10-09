package com.and1ss.user_service.exceptions;

public class InvalidRegisterDataException extends RuntimeException {
    public InvalidRegisterDataException(String message) {
        super(message);
    }
}
