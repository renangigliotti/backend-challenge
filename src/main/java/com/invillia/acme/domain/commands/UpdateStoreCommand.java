package com.invillia.acme.domain.commands;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateStoreCommand {
    private UUID id;
    private String name;
    private String address;
}
