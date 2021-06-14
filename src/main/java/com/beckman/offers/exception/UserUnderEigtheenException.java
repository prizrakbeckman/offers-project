package com.beckman.offers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserUnderEigtheenException extends Exception{
    public UserUnderEigtheenException() {
        super();
    }
    public UserUnderEigtheenException(String message) {
        super(message);
    }
}
