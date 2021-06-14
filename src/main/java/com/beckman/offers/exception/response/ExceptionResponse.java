package com.beckman.offers.exception.response;

import java.time.LocalDate;

public class ExceptionResponse {
    private LocalDate timestamp;
    private String message;
    private String details;

    public ExceptionResponse(LocalDate timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDate getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDetails() {
        return this.details;
    }
}