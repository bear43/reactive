package com.axear.reactive.model;

import org.springframework.http.HttpStatus;

public class ExceptionWrapper {
    private String message;
    private HttpStatus status;
    private Integer statusCode;

    public ExceptionWrapper() {

    }

    public ExceptionWrapper(String message, HttpStatus status, Integer statusCode) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatus(HttpStatus status) {
        statusCode = status.value();
        this.status = status;
    }

    public void setStatusCode(Integer statusCode) {
        status = HttpStatus.valueOf(statusCode);
        this.statusCode = statusCode;
    }
}
