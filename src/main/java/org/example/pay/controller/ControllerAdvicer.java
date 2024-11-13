package org.example.pay.controller;

import org.example.pay.exception.NotEnoughSumException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvicer {

  @ExceptionHandler(value = { NotEnoughSumException.class})
  protected ResponseEntity<Object> handleConflict(NotEnoughSumException exception, WebRequest request) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
  }
}
