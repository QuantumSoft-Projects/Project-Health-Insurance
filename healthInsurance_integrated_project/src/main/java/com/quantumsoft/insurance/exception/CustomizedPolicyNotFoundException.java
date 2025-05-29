package com.quantumsoft.insurance.exception;

public class CustomizedPolicyNotFoundException extends RuntimeException {
    public CustomizedPolicyNotFoundException(String message) {
        super(message);
    }
}
