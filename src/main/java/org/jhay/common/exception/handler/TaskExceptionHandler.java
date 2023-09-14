package org.jhay.common.exception.handler;
import jakarta.servlet.http.HttpServletRequest;
import org.jhay.common.exception.ExceptionResponse;
import org.jhay.common.exception.exceptions.PersonAlreadyExistException;
import org.jhay.common.exception.exceptions.PersonNotFoundException;
import org.jhay.common.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(PersonAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> alreadyExistException(PersonAlreadyExistException e,
                                                                   HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .processedTime(DateUtils.convertLocalDate(LocalDateTime.now()))
                .statusCode(HttpStatus.CONFLICT.value())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(PersonNotFoundException e,
                                                               HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .processedTime(DateUtils.convertLocalDate(LocalDateTime.now()))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> invalidException(MethodArgumentNotValidException e){
        Map<String, String> invalidErrors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error ->{
                    String fieldError = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    invalidErrors.put(fieldError, message);
                });
        return new ResponseEntity<>(invalidErrors, HttpStatus.BAD_REQUEST);
    }
}
