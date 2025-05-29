package com.quantumsoft.insurance.exception;

public class AddOnNotFoundException extends RuntimeException{
    public AddOnNotFoundException(String message){
        super(message);
    }
}
