package com.invillia.acme.application.handlers;

import com.invillia.acme.application.handlers.body.UnprocessableEntity;
import com.invillia.acme.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 400 - UnprocessableEntity
     */

    /*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
    */
}