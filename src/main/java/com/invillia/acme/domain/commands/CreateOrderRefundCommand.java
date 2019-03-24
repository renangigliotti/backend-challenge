package com.invillia.acme.domain.commands;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderRefundCommand {
    private UUID orderId;
}
