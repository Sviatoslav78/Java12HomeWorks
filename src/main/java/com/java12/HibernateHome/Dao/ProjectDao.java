package com.java12.HibernateHome.Dao;

import com.java12.HibernateHome.Entity.Project;
import org.hibernate.SessionFactory;

public class ProjectDao extends DaoBases<Project> {
    public ProjectDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
