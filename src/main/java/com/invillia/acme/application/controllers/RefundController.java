package com.invillia.acme.application.controllers;

import com.invillia.acme.domain.commands.CreateOrderItemRefundCommand;
import com.invillia.acme.domain.commands.CreateOrderRefundCommand;
import com.invillia.acme.domain.services.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.util.UUID;

/**
 * Endpoint REST of Refunds.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
@RestController
@RequestMapping("/refunds")
@Api(value = "Refunds", description = "Refunds controller")
public class RefundController implements Serializable {

    private final PaymentService paymentService;

    @Autowired
    public RefundController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "orders", produces = "application/json")
    public ResponseEntity<Void> RefundOrder(@RequestBody @Valid CreateOrderRefundCommand command) {
        UUID refundId = paymentService.refund(command);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(refundId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "items", produces = "application/json")
    public ResponseEntity RefundOrderItem(@RequestBody @Valid CreateOrderItemRefundCommand command) {
        UUID refundId = paymentService.refund(command);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(refundId).toUri();

        return ResponseEntity.created(location).build();
    }
}
