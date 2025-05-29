package com.quantumsoft.insurance.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdminRecordNotFoundException.class)
    public ResponseEntity<ExceptionResponse> AdminRecordNotFoundException(AdminRecordNotFoundException e, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PolicyNotFoundException.class)
    public ResponseEntity<ExceptionResponse> policyNotFoundException(PolicyNotFoundException e, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomizedPolicyNotFoundException.class)
    public ResponseEntity<ExceptionResponse> customizedPolicyNotFoundException(CustomizedPolicyNotFoundException e, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RiderNotFoundException.class)
    public ResponseEntity<ExceptionResponse> riderNotFoundException(RiderNotFoundException e, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddOnNotFoundException.class)
    public ResponseEntity<ExceptionResponse> addOnNotFoundException(AddOnNotFoundException e, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PremiumRecordNotFoundException.class)
    public ResponseEntity<ExceptionResponse> premiumRecordNotFoundException(PremiumRecordNotFoundException e, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + ex.getMessage());
    }

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ResponseEntity<ExceptionResponse> unAuthorizedAccessException(UnAuthorizedAccessException e, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, e.getMessage(), request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SupportAgentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> supportAgentNotFoundException(SupportAgentNotFoundException e, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
}
