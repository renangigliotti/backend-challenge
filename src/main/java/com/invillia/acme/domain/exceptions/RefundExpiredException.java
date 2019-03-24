package com.invillia.acme.domain.exceptions;

public class RefundExpiredException extends RuntimeException {
    public RefundExpiredException() {
        super("Refund deadline expired");
    }
}
