package com.invillia.acme.domain.commands;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderItemCommand {
    @NotEmpty
    private String description;
    @NotNull
    private BigDecimal price;
}
