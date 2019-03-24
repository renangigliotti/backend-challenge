package com.invillia.acme.domain.repositories;

import com.invillia.acme.domain.entity.OrderItem;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository of OrderItems.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
public interface OrderItemRepository {
    Optional<OrderItem> find(UUID id);

    void create(OrderItem orderItem);
}
