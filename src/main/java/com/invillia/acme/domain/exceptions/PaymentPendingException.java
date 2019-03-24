package com.invillia.acme.domain.exceptions;

public class PaymentPendingException extends RuntimeException {

    public PaymentPendingException() {
        super("Payment is pending");
    }
}
