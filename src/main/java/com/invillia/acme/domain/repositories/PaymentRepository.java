package com.invillia.acme.domain.repositories;

import com.invillia.acme.domain.entity.Payment;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository of Payments.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
public interface PaymentRepository {
    void create(Payment payment);

    Optional<Payment> find(UUID id);

    void update(Payment payment);
}
