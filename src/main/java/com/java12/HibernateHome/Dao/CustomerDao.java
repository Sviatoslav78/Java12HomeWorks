package com.java12.HibernateHome.Dao;

import com.java12.HibernateHome.Entity.Customer;
import org.hibernate.SessionFactory;

public class CustomerDao extends DaoBases<Customer> {
    public CustomerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
