package org.fscl.process.service.function.adapters.upstream.web;

import ono.fscl.core.domain.entity.id.PrefixMismatchException;


import org.fscl.core.ports.upstream.web.error.WebError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FunctionExceptionHandler {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<WebError> handlePrefixMismatch(PrefixMismatchException x) {
        WebError error = new WebError();
        error.setHttpStatus(HttpStatus.BAD_REQUEST);
        error.setUserMessage(x.getMessage());
        return new ResponseEntity<WebError>(error, HttpStatus.BAD_REQUEST);
    }
}
