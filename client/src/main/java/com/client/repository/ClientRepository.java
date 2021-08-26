package com.client.repository;

import com.client.entity.ClientEntity;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Singleton
public class ClientRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public ClientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @ReadOnly
    public Optional<ClientEntity> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(ClientEntity.class, id));
    }

    @Transactional
    public ClientEntity save(@NotBlank String name) {
        ClientEntity item = new ClientEntity();
        item.setName(name);
        entityManager.persist(item);
        return item;
    }

    @Transactional
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @ReadOnly
    public List<ClientEntity> findAll() {
        String qlString = "SELECT g FROM ClientEntity as g";
        TypedQuery<ClientEntity> query = entityManager.createQuery(qlString, ClientEntity.class);
        return query.getResultList();
    }

    @Transactional
    public int update(@NotNull Long id, @NotBlank String name, @NotBlank String description) {
        return entityManager.createQuery("UPDATE ClientEntity g SET name = :name, description = :description where id = :id")
                .setParameter("name", name)
                .setParameter("description", description)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public ClientEntity saveWithException(@NotBlank String name) {
        save(name);
        throw new PersistenceException();
    }
}
