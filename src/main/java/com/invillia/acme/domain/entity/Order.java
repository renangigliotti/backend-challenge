package com.invillia.acme.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orders")
    private Long id;

    @ManyToOne
    private Store store;

    private String address;

    private LocalDateTime confirmationDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne
    private Payment payment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderItem> items;
}
