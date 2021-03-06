package com.invillia.acme.domain.services;

import com.invillia.acme.domain.commands.CreateOrderItemRefundCommand;
import com.invillia.acme.domain.commands.CreateOrderRefundCommand;
import com.invillia.acme.domain.commands.CreatePaymentCommand;
import com.invillia.acme.domain.entity.*;
import com.invillia.acme.domain.exceptions.*;
import com.invillia.acme.domain.repositories.OrderItemRepository;
import com.invillia.acme.domain.repositories.OrderRepository;
import com.invillia.acme.domain.repositories.PaymentRepository;
import com.invillia.acme.domain.repositories.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    private final int REFUND_EXPIRES = 10;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final PaymentRepository paymentRepository;

    private final RefundRepository refundRepository;

    @Autowired
    public PaymentService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, PaymentRepository paymentRepository, RefundRepository refundRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentRepository = paymentRepository;
        this.refundRepository = refundRepository;
    }

    @Transactional
    public UUID create(CreatePaymentCommand command) {
        Order order = orderRepository.find(command.getOrderId()).orElseThrow(NotFoundException::new);

        Payment payment = new Payment();
        payment.setCreditCardNumber(command.getCreditCardNumber());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setOrder(order);

        paymentRepository.create(payment);

        order.setPayment(payment);
        order.setStatus(OrderStatus.PAYMENT_ACCEPT);

        orderRepository.update(order);

        return payment.getId();
    }

    @Transactional
    public UUID refund(CreateOrderRefundCommand command) {
        Order order = orderRepository.find(command.getOrderId()).orElseThrow(NotFoundException::new);

        validateOrderToRefund(order);

        Payment payment = Optional.ofNullable(order.getPayment()).orElseThrow(PaymentRequiredException::new);

        validatePaymentToRefund(payment);

        Refund refund = new Refund();
        refund.setOrder(order);

        refundRepository.create(refund);

        order.setStatus(OrderStatus.REFUNDED);
        orderRepository.update(order);

        return refund.getId();
    }

    @Transactional
    public UUID refund(CreateOrderItemRefundCommand command) {
        OrderItem orderItem = orderItemRepository.find(command.getOrderItemId()).orElseThrow(NotFoundException::new);

        validateOrderToRefund(orderItem.getOrder());

        Payment payment = Optional.of(orderItem.getOrder().getPayment()).orElseThrow(PaymentRequiredException::new);

        validatePaymentToRefund(payment);

        Refund refund = new Refund();
        refund.setOrderItem(orderItem);

        refundRepository.create(refund);

        return refund.getId();
    }

    @Transactional
    public void complete(UUID id) {
        Payment payment = paymentRepository.find(id).orElseThrow(NotFoundException::new);

        payment.setStatus(PaymentStatus.COMPLETED);
        paymentRepository.update(payment);

        Order order = orderRepository.find(payment.getOrder().getId()).orElseThrow(NotFoundException::new);
        order.setStatus(OrderStatus.TRANSPORT);
        order.setConfirmationDate(LocalDateTime.now());

        orderRepository.update(order);
    }

    private void validateOrderToRefund(Order order) {
        if (order.getStatus().equals(OrderStatus.PAYMENT_PENDING))
            throw new PaymentRequiredException();

        if (order.getStatus().equals(OrderStatus.PAYMENT_ACCEPT))
            throw new PaymentPendingException();

        if (order.getStatus().equals(OrderStatus.CANCELLED))
            throw new OrderCanceledException();

        if (order.getConfirmationDate().plusDays(REFUND_EXPIRES).isBefore(LocalDateTime.now()))
            throw new RefundExpiredException();
    }

    private void validatePaymentToRefund(Payment payment) {
        if (!payment.getStatus().equals(PaymentStatus.COMPLETED))
            throw new PaymentPendingException();
    }
}
