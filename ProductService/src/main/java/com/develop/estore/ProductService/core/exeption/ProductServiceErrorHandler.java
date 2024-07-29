package com.develop.estore.ProductService.core.exeption;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author admin
 */
@ControllerAdvice
public class ProductServiceErrorHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<CustomErrorRes> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new CustomErrorRes(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<CustomErrorRes> handleException(Exception e) {
        return new ResponseEntity<>(new CustomErrorRes(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {CommandExecutionException.class})
    public ResponseEntity<CustomErrorRes> handleCommandExecutionException(CommandExecutionException e) {
        return new ResponseEntity<>(new CustomErrorRes(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
