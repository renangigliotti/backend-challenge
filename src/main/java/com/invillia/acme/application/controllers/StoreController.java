package com.invillia.acme.application.controllers;

import com.invillia.acme.domain.commands.CreateStoreCommand;
import com.invillia.acme.domain.commands.UpdateStoreCommand;
import com.invillia.acme.domain.querys.StoreQuery;
import com.invillia.acme.domain.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

/**
 * Endpoint REST of Stores.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
@RestController
@RequestMapping("/stores")
public class StoreController implements Serializable {

    @Autowired
    private StoreService storeService;

    @GetMapping()
    public ResponseEntity<List<StoreQuery>> List(@RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "address", required = false) String address) {
        return ResponseEntity.ok(storeService.list(name, address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreQuery> Find(@PathVariable("id") Long id) {
        final StoreQuery store = storeService.find(id);

        if (store == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(store);
    }

    @PostMapping()
    public ResponseEntity<Void> Create(@RequestBody CreateStoreCommand command) {
        final Long id = storeService.create(command);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> Update(@PathVariable("id") Long id, @RequestBody UpdateStoreCommand command) {
        if (command.getId() != id)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        storeService.update(command);

        return ResponseEntity.noContent().build();
    }
}
