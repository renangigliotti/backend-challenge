package com.invillia.acme.domain.commands;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateOrderCommand {

    private String address;
    private UUID storeId;
    private List<OrderItemCommand> items;
}
