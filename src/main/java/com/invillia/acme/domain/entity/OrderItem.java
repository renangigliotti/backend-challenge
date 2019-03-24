package com.invillia.acme.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "orders_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orderItems")
    private Long id;

    private String description;

    private BigDecimal price;

    @ManyToOne
    private Order order;
}
