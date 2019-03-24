package com.invillia.acme.domain.exceptions;

public class OrderCanceledException extends RuntimeException {

    public OrderCanceledException() {
        super("Order canceled");
    }
}
