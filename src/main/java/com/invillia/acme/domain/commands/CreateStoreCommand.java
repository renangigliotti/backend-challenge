package com.invillia.acme.domain.commands;

import lombok.Data;

@Data
public class CreateStoreCommand {

    private String name;
    private String address;
}
