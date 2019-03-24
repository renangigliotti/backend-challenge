package com.invillia.acme.domain.repositories;

import com.invillia.acme.domain.entity.Payment;

import java.util.Optional;

/**
 * Repository of Payments.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
public interface PaymentRepository {
    void create(Payment payment);

    Optional<Payment> find(Long id);

    void update(Payment payment);
}
