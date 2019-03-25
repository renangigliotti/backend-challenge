package com.invillia.acme.domain.commands;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CreateOrderItemRefundCommand {
    @NotNull
    public UUID orderItemId;
}
