package com.invillia.acme.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String creditCardNumber;

    @OneToOne
    private Order order;

    private LocalDateTime paymentDate;
}
