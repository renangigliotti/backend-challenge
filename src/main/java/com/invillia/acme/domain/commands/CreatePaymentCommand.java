package com.invillia.acme.domain.commands;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CreatePaymentCommand {
    @NotNull
    private UUID orderId;
    @NotEmpty
    private String creditCardNumber;
}
