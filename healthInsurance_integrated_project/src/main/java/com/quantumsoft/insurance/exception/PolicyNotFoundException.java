package com.quantumsoft.insurance.exception;

public class PolicyNotFoundException extends RuntimeException {
    public PolicyNotFoundException(String msg) {
        super(msg);
    }
}
