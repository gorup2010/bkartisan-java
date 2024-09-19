package com.bkartisan.be.ExceptionHandler;

public class InvalidVerificationCodeException extends RuntimeException {
    public InvalidVerificationCodeException() {
        super("Invalid email verification code");
    }
}
