package com.invillia.acme.application.controllers;

import com.invillia.acme.domain.commands.CreatePaymentCommand;
import com.invillia.acme.domain.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;

/**
 * Endpoint REST of Payments.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
@RestController
@RequestMapping("/payments")
public class PaymentController implements Serializable {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public ResponseEntity<Void> Create(@RequestBody CreatePaymentCommand command) {
        Long id = paymentService.create(command);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> Complete(@PathVariable Long id) {
        paymentService.complete(id);

        return ResponseEntity.noContent().build();
    }
}
