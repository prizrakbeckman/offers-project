package com.beckman.offers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FailedAccountInsertionException extends RuntimeException {
    public FailedAccountInsertionException(String message) {
        super(message);
    }
}