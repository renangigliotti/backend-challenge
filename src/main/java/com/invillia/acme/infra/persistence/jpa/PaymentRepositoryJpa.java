package com.invillia.acme.infra.persistence.jpa;

import com.invillia.acme.domain.entity.Payment;
import com.invillia.acme.domain.repositories.PaymentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PaymentRepositoryJpa implements PaymentRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void create(Payment payment) {
        entityManager.persist(payment);
        entityManager.flush();
    }
}
