package com.example.urbanmobility.exception;

public class InvalidPermissionException extends RuntimeException {

    public InvalidPermissionException(String message) {
        super(message);
    }

    public InvalidPermissionException(Throwable cause) {
        super(cause);
    }
}