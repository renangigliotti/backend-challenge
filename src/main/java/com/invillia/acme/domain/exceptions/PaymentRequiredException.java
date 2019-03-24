package com.invillia.acme.domain.exceptions;

public class PaymentRequiredException extends RuntimeException {
    public PaymentRequiredException() {
        super("Payment required");
    }
}
