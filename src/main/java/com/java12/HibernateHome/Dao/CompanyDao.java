package com.java12.HibernateHome.Dao;

import com.java12.HibernateHome.Entity.Company;
import org.hibernate.SessionFactory;

public class CompanyDao extends DaoBases<Company> {
    public CompanyDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
