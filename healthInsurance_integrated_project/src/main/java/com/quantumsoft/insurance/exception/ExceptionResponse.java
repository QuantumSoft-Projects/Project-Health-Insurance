package com.quantumsoft.insurance.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class ExceptionResponse {

    private LocalDateTime date;
    private Integer httpStatusCode;
    private HttpStatus httpStatusMessage;
    private String customMessage;
    private String apiPath;

    public ExceptionResponse(LocalDateTime date, Integer httpStatusCode, HttpStatus httpStatusMessage, String customMessage, String apiPath) {
        this.date = date;
        this.httpStatusCode = httpStatusCode;
        this.httpStatusMessage = httpStatusMessage;
        this.customMessage = customMessage;
        this.apiPath = apiPath;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatusMessage() {
        return httpStatusMessage;
    }

    public void setHttpStatusMessage(HttpStatus httpStatusMessage) {
        this.httpStatusMessage = httpStatusMessage;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }
}
