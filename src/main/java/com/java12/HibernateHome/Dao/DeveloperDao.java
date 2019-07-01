package com.java12.HibernateHome.Dao;

import com.java12.HibernateHome.Entity.Developer;
import org.hibernate.SessionFactory;

public class DeveloperDao extends DaoBases<Developer> {

    public DeveloperDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
