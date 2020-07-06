package com.thoughtworks.exam.bff.adapter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity handleHttpClientErrorException(
            HttpClientErrorException ex) {
        return new ResponseEntity(ex.getResponseBodyAsString(), ex.getResponseHeaders(), ex.getStatusCode());
    }
}