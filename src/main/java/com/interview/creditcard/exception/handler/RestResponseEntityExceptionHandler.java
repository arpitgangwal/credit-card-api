package com.interview.creditcard.exception.handler;

import com.interview.creditcard.exception.ValidationFailedResponse;
import com.interview.creditcard.exception.ViolationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler{
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationFailedResponse onConstraintValidationException(ConstraintViolationException e) {
        ValidationFailedResponse error = new ValidationFailedResponse();
        for (ConstraintViolation violation: e.getConstraintViolations()) {
            error.getViolations().add(new ViolationErrors(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationFailedResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationFailedResponse error = new ValidationFailedResponse();
        for (FieldError fieldError: e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new ViolationErrors(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }
   @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<Object> handleGeneralException(
            RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ViolationErrors("Internal Server Error",ex.getMessage()));

    }
}
