package com.invillia.acme.domain.repositories;

import com.invillia.acme.domain.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository of Orders.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
public interface OrderRepository {
    void create(Order order);

    Optional<Order> find(UUID orderId);

    List<Order> list();

    void update(Order order);
}
