package com.java12.HibernateHome.Dao;

import java.util.List;

public interface CRUDInterface<T> {

    String addToTable(T entity);

    String deleteById(long id);

    T getById(long id);

    List<T> getAll();

    String update(T entity);
}
