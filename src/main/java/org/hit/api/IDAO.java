package org.hit.api;

import java.util.List;

public interface IDAO<T> {

    boolean save(T model);

    boolean update(T model);

    boolean delete(T model);

    T getById(String id);

    List<T> getAll();
}