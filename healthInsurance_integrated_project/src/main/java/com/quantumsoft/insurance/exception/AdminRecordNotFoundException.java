package com.quantumsoft.insurance.exception;

public class AdminRecordNotFoundException extends RuntimeException{
    public AdminRecordNotFoundException(String message){
        super(message);
    }
}
