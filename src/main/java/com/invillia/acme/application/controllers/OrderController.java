package com.invillia.acme.application.controllers;

import com.invillia.acme.domain.commands.CreateOrderCommand;
import com.invillia.acme.domain.queries.OrderQuery;
import com.invillia.acme.domain.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;


/**
 * Endpoint REST of Orders.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
@RestController
@RequestMapping("/orders")
public class OrderController implements Serializable {

    @Autowired
    private OrderService orderService;


    @GetMapping()
    public ResponseEntity<List<OrderQuery>> List() {
        return ResponseEntity.ok(orderService.list());
    }

    @PostMapping()
    public ResponseEntity<Void> Create(@RequestBody CreateOrderCommand command) {
        Long id = orderService.create(command);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }

}
