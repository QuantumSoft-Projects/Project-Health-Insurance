package com.quantumsoft.insurance.exception;

public class RiderNotFoundException  extends RuntimeException{
    public RiderNotFoundException(String message){
        super(message);
    }
}
