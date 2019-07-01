package com.java12.HibernateHome.Service;

public interface ServiceInterface<T> {
    String infoType = "Operation successful";
    String errorType = "Error is: ";

    T getById(long id);

    String getByName(String name);

    String createNew(T entity);

    String deleteById(long id);

    String update(T entity);

    String getAll();






}
