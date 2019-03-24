package com.invillia.acme.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "refunds")
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_refunds")
    private Long id;

    @OneToOne
    private Order order;

    @OneToOne
    private OrderItem orderItem;
}
