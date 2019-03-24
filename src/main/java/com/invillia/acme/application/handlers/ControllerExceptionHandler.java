package com.invillia.acme.application.handlers;

import com.invillia.acme.application.handlers.body.UnprocessableEntity;
import com.invillia.acme.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * 422 - UnprocessableEntity
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({PaymentRequiredException.class, RefundExpiredException.class, OrderCanceledException.class, PaymentPendingException.class})
    public ResponseEntity<UnprocessableEntity> handlePaymentRequiredException(RuntimeException exception) {
        return ResponseEntity.unprocessableEntity().body(new UnprocessableEntity(exception.getMessage()));
    }
}