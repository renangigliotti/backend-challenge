package com.invillia.acme.domain.commands;

import java.util.List;

public class CreateOrderCommand {

    private String address;
    private Long storeId;
    private List<OrdemItenCommand> items;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<OrdemItenCommand> getItems() {
        return items;
    }

    public void setItems(List<OrdemItenCommand> items) {
        this.items = items;
    }
}
