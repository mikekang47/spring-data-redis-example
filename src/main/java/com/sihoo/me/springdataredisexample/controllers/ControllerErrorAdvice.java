package com.sihoo.me.springdataredisexample.controllers;

import com.sihoo.me.springdataredisexample.dto.ErrorResponse;
import com.sihoo.me.springdataredisexample.error.UserCausedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerErrorAdvice {
    @ExceptionHandler(UserCausedException.class)
    public ResponseEntity<ErrorResponse> handleUserCausedException(UserCausedException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()),e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
