package com.invillia.acme.domain.services;

import com.invillia.acme.domain.commands.CreateStoreCommand;
import com.invillia.acme.domain.commands.UpdateStoreCommand;
import com.invillia.acme.domain.entity.Store;
import com.invillia.acme.domain.exceptions.NotFoundException;
import com.invillia.acme.domain.queries.StoreQuery;
import com.invillia.acme.domain.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service of Stores.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public Long create(CreateStoreCommand command) {
        final Store store = new Store();
        store.setName(command.getName());
        store.setAddress(command.getAddress());

        storeRepository.create(store);

        return store.getId();
    }

    @Transactional
    public void update(UpdateStoreCommand command) {
        final Store store = storeRepository.find(command.getId()).orElseThrow(NotFoundException::new);
        store.setName(command.getName());
        store.setAddress(command.getAddress());

        storeRepository.update(store);
    }

    public List<StoreQuery> list(String name, String address) {
        return storeRepository.list(name, address).stream().map(s -> new StoreQuery(s.getId(), s.getName(), s.getAddress())).collect(Collectors.toList());
    }

    public StoreQuery find(Long id) {
        final Store store = storeRepository.find(id).orElseThrow(NotFoundException::new);

        return new StoreQuery(store.getId(), store.getName(), store.getAddress());
    }
}
