package com.java12.HibernateHome.Dao;

import com.java12.HibernateHome.Entity.Skill;
import org.hibernate.SessionFactory;

public class SkillDao extends DaoBases<Skill> {
    public SkillDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
