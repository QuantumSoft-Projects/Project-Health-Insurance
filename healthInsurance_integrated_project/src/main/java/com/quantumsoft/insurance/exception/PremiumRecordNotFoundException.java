package com.quantumsoft.insurance.exception;

public class PremiumRecordNotFoundException extends RuntimeException{
    public PremiumRecordNotFoundException(String message){
        super(message);
    }
}
