package com.invillia.acme.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_payments")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String creditCardNumber;

    @OneToOne
    private Order order;

    private LocalDateTime paymentDate;
}
