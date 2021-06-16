package com.beckman.offers.controller.advice;

import com.beckman.offers.exception.UserExistingInDatabaseException;
import com.beckman.offers.exception.UserNotFoundException;
import com.beckman.offers.exception.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

class EndpointExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<? extends Exception>  handleAllExceptions(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse= new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<? extends Exception>  handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse= new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserExistingInDatabaseException.class)
    public final ResponseEntity<? extends Exception> handleUserExistInDatabaseException(UserExistingInDatabaseException ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse= new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        ExceptionResponse exceptionResponse= new ExceptionResponse(LocalDate.now(), ex.getMessage(), ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
