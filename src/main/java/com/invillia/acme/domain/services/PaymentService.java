package com.invillia.acme.domain.services;

import com.invillia.acme.domain.commands.CreatePaymentCommand;
import com.invillia.acme.domain.entity.Order;
import com.invillia.acme.domain.entity.OrderStatus;
import com.invillia.acme.domain.entity.Payment;
import com.invillia.acme.domain.entity.PaymentStatus;
import com.invillia.acme.domain.exceptions.NotFoundException;
import com.invillia.acme.domain.repositories.OrderRepository;
import com.invillia.acme.domain.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public Long create(CreatePaymentCommand command) {
        Order order = orderRepository.find(command.getOrderId()).orElseThrow(NotFoundException::new);

        Payment payment = new Payment();
        payment.setCreditCardNumber(command.getCreditCardNumber());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setOrder(order);

        paymentRepository.create(payment);

        order.setPayment(payment);
        order.setStatus(OrderStatus.PAYMENT_ACCEPT);
        order.setConfirmationDate(LocalDateTime.now());
        orderRepository.update(order);

        return payment.getId();
    }
}
