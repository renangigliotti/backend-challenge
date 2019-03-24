package com.invillia.acme.domain.entity;

public enum OrderStatus {
    PAYMENT_PENDING,
    PAYMENT_ACCEPT,
    TRANSPORT,
    DELIVERED,
    REDUNDED,
    CANCELLED;
}