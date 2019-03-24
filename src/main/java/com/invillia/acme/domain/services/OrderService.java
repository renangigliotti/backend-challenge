package com.invillia.acme.domain.services;

import com.invillia.acme.domain.commands.CreateOrderCommand;
import com.invillia.acme.domain.entity.Order;
import com.invillia.acme.domain.entity.OrderItem;
import com.invillia.acme.domain.entity.OrderStatus;
import com.invillia.acme.domain.entity.Store;
import com.invillia.acme.domain.exceptions.NotFoundException;
import com.invillia.acme.domain.querys.OrderQuery;
import com.invillia.acme.domain.repositories.OrderItemRepository;
import com.invillia.acme.domain.repositories.OrderRepository;
import com.invillia.acme.domain.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service of Orders.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
@Service
public class OrderService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public Long create(CreateOrderCommand command) {
        Store store = storeRepository.find(command.getStoreId()).orElseThrow(NotFoundException::new);

        Order order = new Order();
        order.setStore(store);
        order.setAddress(command.getAddress());
        order.setStatus(OrderStatus.PAYMENT_PENDING);
        order.setPayment(null);
        order.setConfirmationDate(null);

        orderRepository.create(order);

        command.getItems().forEach(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setDescription(item.getDescription());
            orderItem.setPrice(item.getPrice());

            orderItemRepository.create(orderItem);
        });

        return order.getId();
    }

    public List<OrderQuery> list() {
        return orderRepository.list().stream().map(o -> new OrderQuery(o.getId(), o.getStore().getId(), o.getAddress(), o.getStatus().toString())).collect(Collectors.toList());
    }
}
