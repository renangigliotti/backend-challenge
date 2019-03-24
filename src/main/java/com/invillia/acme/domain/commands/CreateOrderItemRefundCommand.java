package com.invillia.acme.domain.commands;

public class CreateOrderItemRefundCommand {
    public Long orderItemId;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }
}
