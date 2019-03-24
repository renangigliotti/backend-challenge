package com.invillia.acme.infra.persistence.jpa;

import com.invillia.acme.domain.entity.Order;
import com.invillia.acme.domain.entity.QOrder;
import com.invillia.acme.domain.repositories.OrderRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepositoryJpa implements OrderRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void create(Order order) {
        entityManager.persist(order);
        entityManager.flush();
    }

    @Override
    public Optional<Order> find(UUID id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> list() {
        QOrder qOrder = QOrder.order;

        return new JPAQueryFactory(entityManager)
                .select(qOrder)
                .from(qOrder)
                .innerJoin(qOrder.store).fetchJoin()
                .leftJoin(qOrder.payment).fetchJoin()
                .fetch();
    }

    @Override
    public void update(Order order) {
        entityManager.merge(order);
        entityManager.flush();
    }
}
