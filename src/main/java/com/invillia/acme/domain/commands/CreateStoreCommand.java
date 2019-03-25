package com.invillia.acme.domain.commands;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateStoreCommand {
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
}
