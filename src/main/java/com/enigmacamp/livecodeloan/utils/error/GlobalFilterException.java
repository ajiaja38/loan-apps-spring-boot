package com.enigmacamp.livecodeloan.utils.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.enigmacamp.livecodeloan.utils.res.ResponseMessage;

@RestControllerAdvice
public class GlobalFilterException extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ResponseMessage> handleResponseStatusException(ResponseStatusException exception) {
    return ResponseEntity
    .status(exception.getStatusCode())
    .body(
      new ResponseMessage(
        exception.getStatusCode().value(),
        exception.getReason()
      )
    );
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ResponseMessage> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
    return ResponseEntity
    .status(HttpStatus.CONFLICT)
    .body(
      new ResponseMessage(
        HttpStatus.CONFLICT.value(),
        "User Already Exist"
      )
    );
  }

}
