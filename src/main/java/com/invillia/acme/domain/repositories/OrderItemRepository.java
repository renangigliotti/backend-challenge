package com.invillia.acme.domain.repositories;

import com.invillia.acme.domain.entity.OrderItem;

import java.util.Optional;

/**
 * Repository of OrderItems.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
public interface OrderItemRepository {
    Optional<OrderItem> find(Long id);

    void create(OrderItem orderItem);
}
