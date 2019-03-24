package com.invillia.acme.application.handlers;

import com.invillia.acme.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.Serializable;

/**
 * Capture and treat as exceptions
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
@RestControllerAdvice
public class ControllerExceptionHandler implements Serializable {

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoHandlerFoundException.class, MethodArgumentTypeMismatchException.class, NotFoundException.class})
    public void handleNotFoundException() {
    }
}
