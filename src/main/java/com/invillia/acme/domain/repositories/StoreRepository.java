package com.invillia.acme.domain.repositories;

import com.invillia.acme.domain.entity.Store;

import java.util.List;
import java.util.Optional;

/**
 * Repository of Stores.
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
public interface StoreRepository {
    void create(Store store);

    void update(Store store);

    List<Store> list(String name, String address);

    Optional<Store> find(Long id);
}
