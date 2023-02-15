package com.phonebook.controller;

import com.phonebook.exception.NotFoundException;
import com.phonebook.helper.ResponseHelper;
import com.phonebook.model.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Object>> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            String field = null;
            for (Path.Node node : constraintViolation.getPropertyPath()) {
                field = node.getName();
            }

            errors.put(field, List.of(constraintViolation.getMessage()));
        }

        return ResponseHelper.buildResponseEntity(HttpStatus.BAD_REQUEST, null, errors);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<APIResponse<Object>> notFoundExceptionHandler(NotFoundException ex) {
        return ResponseHelper.buildResponseEntity(HttpStatus.NOT_FOUND, null, ex.getErrors());
    }

}
