package com.example.urbanmobility.exception;

public class PaymentDeclinedException extends RuntimeException {

    public PaymentDeclinedException(String message) {
        super(message);
    }

    public PaymentDeclinedException(Throwable cause) {
        super(cause);
    }
}
