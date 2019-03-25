package com.invillia.acme.application.controllers;

import com.invillia.acme.domain.commands.CreatePaymentCommand;
import com.invillia.acme.domain.services.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.util.UUID;

/**
 * Endpoint REST of Payments.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
@RestController
@RequestMapping("/payments")
@Api(value = "Payments", description = "Payments controller")
public class PaymentController implements Serializable {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public ResponseEntity<Void> Create(@RequestBody @Valid CreatePaymentCommand command) {
        UUID id = paymentService.create(command);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> Complete(@PathVariable UUID id) {
        paymentService.complete(id);

        return ResponseEntity.noContent().build();
    }
}
