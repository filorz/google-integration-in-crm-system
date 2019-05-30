package com.crmpetproject.crmpetproject.servives.interfaces;

import java.util.List;

public interface CommonService<T> {
    T get(Long id);

    T add(T entity);

    List<T> getAll();

    void update(T entity);

    void delete(Long id);

    void delete(T entity);
}