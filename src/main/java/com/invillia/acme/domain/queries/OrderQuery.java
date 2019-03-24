package com.invillia.acme.domain.queries;

public class OrderQuery {
    private Long id;

    private Long storeId;

    private String address;

    private String status;

    public OrderQuery(Long id, Long storeId, String address, String status) {
        this.id = id;
        this.storeId = storeId;
        this.address = address;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
