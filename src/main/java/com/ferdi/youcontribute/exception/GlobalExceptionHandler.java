package com.ferdi.youcontribute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedRepositoryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    //Http status duplicate -> 409 (CONFLICT) , Sayfaya erişim yasak -> 403  (FORBİDDEN)
    @ResponseBody
    public ErrorResponse handleDuplicateRepositoryException(DuplicatedRepositoryException exception, HttpServletRequest request) {
        return ErrorResponse.builder().message(exception.getMessage()).build();
    }
}
