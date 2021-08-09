package com.store.repository;

import com.store.entity.ItemEntity;
import io.micronaut.transaction.annotation.ReadOnly;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Singleton
public class ItemRepository {

    private final EntityManager entityManager;

    public ItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @ReadOnly
    public Optional<ItemEntity> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(ItemEntity.class, id));
    }

    @Transactional
    public ItemEntity save(@NotBlank String name) {
        ItemEntity item = new ItemEntity();
        item.setName(name);
        entityManager.persist(item);
        return item;
    }

    @Transactional
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @ReadOnly
    public List<ItemEntity> findAll() {
        String qlString = "SELECT g FROM ItemEntity as g";
        TypedQuery<ItemEntity> query = entityManager.createQuery(qlString, ItemEntity.class);
        return query.getResultList();
    }

    @Transactional
    public int update(@NotNull Long id, @NotBlank String name, @NotBlank String description) {
        return entityManager.createQuery("UPDATE ItemEntity g SET name = :name, description = :description where id = :id")
                .setParameter("name", name)
                .setParameter("description", description)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public ItemEntity saveWithException(@NotBlank String name) {
        save(name);
        throw new PersistenceException();
    }
}
