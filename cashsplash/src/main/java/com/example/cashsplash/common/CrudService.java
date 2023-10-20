package com.example.cashsplash.common;

import java.util.Optional;
import java.util.UUID;

public interface CrudService<T> {

    Optional<T> save(T entity);

    Optional<T> update(UUID uuid, T entity);

    boolean delete(UUID uuid);

    Optional<T> findById(UUID uuid);

    Iterable<T> findAll();
}