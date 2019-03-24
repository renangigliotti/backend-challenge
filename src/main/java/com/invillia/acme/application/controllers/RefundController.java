package com.invillia.acme.application.controllers;

import com.invillia.acme.domain.commands.CreateOrderItemRefundCommand;
import com.invillia.acme.domain.commands.CreateOrderRefundCommand;
import com.invillia.acme.domain.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;

/**
 * Endpoint REST of Refunds.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
@RestController
@RequestMapping("/refunds")
public class RefundController implements Serializable {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("orders")
    public ResponseEntity<Void> RefundOrder(@RequestBody CreateOrderRefundCommand command) {
        Long refundId = paymentService.refund(command);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(refundId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("items")
    public ResponseEntity RefundOrderItem(@RequestBody CreateOrderItemRefundCommand command) {
        Long refundId = paymentService.refund(command);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(refundId).toUri();

        return ResponseEntity.created(location).build();
    }
}
