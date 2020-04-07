package com.tree.pos.exception;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
            Map<String,Object> body = new LinkedHashMap<>();
            Map<String, Object> subBody = new HashMap<>();
            body.put("timestamp", new Date());

            body.put("status", status.value());
            List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
            for(FieldError fieldError : fieldErrors){
                String field = fieldError.getField();
                String errorMessages = fieldError.getDefaultMessage();
                subBody.put(field, errorMessages);
            }
            body.put("errors", subBody);


        return new ResponseEntity<>(body, headers, status);
    }
    
}