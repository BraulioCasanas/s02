package com.clients.repository;

import com.clients.entity.ClientEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClientRepository {

    @Inject
    EntityManager entityManager;

    public Optional<ClientEntity> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(ClientEntity.class, id));
    }

    @Transactional
    public ClientEntity save(@NotBlank String name, String city, boolean isOlder) {
        ClientEntity item = new ClientEntity();
        item.setName(name);
        item.setCity(city);
        item.setOlder(isOlder);
        entityManager.persist(item);
        return item;
    }

    @Transactional
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    public List<ClientEntity> findAll() {
        String qlString = "SELECT g FROM ClientEntity as g";
        TypedQuery<ClientEntity> query = entityManager.createQuery(qlString, ClientEntity.class);
        return query.getResultList();
    }

    @Transactional
    public int update(@NotNull Long id, @NotBlank String name, @NotBlank boolean isOlder) {
        return entityManager.createQuery("UPDATE ClientEntity g SET name = :name, isOlder = :isOlder where id = :id")
                .setParameter("name", name)
                .setParameter("isOlder", isOlder)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public ClientEntity saveWithException(@NotBlank String name) {
        save(name, "", false);
        throw new PersistenceException();
    }
}
