package com.invillia.acme.domain.commands;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class CreateOrderCommand {
    @NotEmpty
    private String address;
    @NotNull
    private UUID storeId;
    @NotNull
    private List<OrderItemCommand> items;
}
