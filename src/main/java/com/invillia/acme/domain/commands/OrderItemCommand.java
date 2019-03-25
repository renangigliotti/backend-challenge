package com.invillia.acme.domain.commands;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemCommand {

    private String description;
    private BigDecimal price;
}
