package com.invillia.acme.domain.commands;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UpdateStoreCommand {
    @NotNull
    private UUID id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
}
