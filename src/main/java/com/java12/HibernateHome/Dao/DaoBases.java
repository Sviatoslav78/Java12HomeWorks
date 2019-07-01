package com.java12.HibernateHome.Dao;

import com.java12.HibernateHome.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class DaoBases<T extends Object> implements CRUDInterface<T> {
    private final String infoMessage = "Successes operation: entity type: " + getTType().getName();
    private final String errorMessage = "Error with ";

    private SessionFactory sessionFactory;
    private Session currentSession;
    private Transaction transaction;

    public DaoBases(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private void openSession(boolean trans) {
        currentSession = HibernateFactory.HIBERNATE_FACTORY.getSessionFactory().openSession();
        if(trans){
           transaction = currentSession.beginTransaction();
        }
    }

    private void commitAndClose(){
        transaction.commit();
        currentSession.close();
    }


    @Override
    public String addToTable(T entity) {
        openSession(true);
        currentSession.save(entity);
        commitAndClose();
        return infoMessage + " is add to the table";
    }

    @Override
    public String deleteById(long id) {
        openSession(true);
        T willBeDeleted = getById(id);
        if(willBeDeleted != null){
            currentSession.delete(willBeDeleted);
            commitAndClose();
            return infoMessage + " just been deleted from table" ;
        }
        return errorMessage + " that element don`t founded";
    }

    @Override
    public T getById(long id) {
        openSession(false);
        T element = (T) currentSession.get(getTType(), id);
        currentSession.close();
        return element;
    }

    @Override
    public List<T> getAll() {
        openSession(false);
        List<T> result = currentSession.createQuery("from " + getTType().getName()).list();
        currentSession.close();
        return result;
    }

    @Override
    public String update(T entity) {
        openSession(true);
        currentSession.update(entity);
        commitAndClose();
        return infoMessage + " just been updated";
    }

    private Class getTType(){
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        Class founded = null;
        try {
            founded = Class.forName(type.getActualTypeArguments()[0].getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return founded;
    }
}
