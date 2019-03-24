package com.invillia.acme.domain.commands;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderItemRefundCommand {
    public UUID orderItemId;
}
