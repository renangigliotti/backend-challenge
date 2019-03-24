package com.invillia.acme.infra.persistence.jpa;

import com.invillia.acme.domain.entity.QStore;
import com.invillia.acme.domain.entity.Store;
import com.invillia.acme.domain.repositories.StoreRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StoreRepositoryJpa implements StoreRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void create(Store store) {
        entityManager.persist(store);
        entityManager.flush();
    }

    @Override
    public void update(Store store) {
        entityManager.merge(store);
        entityManager.flush();
    }

    @Override
    public List<Store> list(String name, String address) {
        QStore store = QStore.store;

        JPAQuery<Store> query = new JPAQueryFactory(entityManager)
                .select(store)
                .from(store);

        if (name != null && !name.isEmpty())
            query.where(store.name.eq(name));

        if (address != null && !address.isEmpty())
            query.where(store.address.eq(address));

        return query.fetch();
    }

    @Override
    public Optional<Store> find(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(entityManager.find(Store.class, id));
    }
}