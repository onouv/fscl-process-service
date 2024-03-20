package org.ono.fscl.process.service.function.adapters.upstream.web;

import org.ono.fscl.process.openapi.model.OasErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FunctionExceptionHandler {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<OasErrorDto> handleIllegalArgument(IllegalArgumentException e)
}
