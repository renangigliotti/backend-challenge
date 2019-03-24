package com.invillia.acme.domain.commands;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePaymentCommand {
    private UUID orderId;
    private String creditCardNumber;
}
