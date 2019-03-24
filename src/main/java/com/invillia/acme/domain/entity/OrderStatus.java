package com.invillia.acme.domain.entity;

public enum OrderStatus {
    PAYMENT_PENDING,
    PAYMENT_ACCEPT,
    TRANSPORT,
    DELIVERED,
    REFUNDED,
    CANCELLED;
}