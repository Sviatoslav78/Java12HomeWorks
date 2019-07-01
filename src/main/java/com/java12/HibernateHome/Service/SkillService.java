package com.java12.HibernateHome.Service;

import com.java12.HibernateHome.Dao.SkillDao;
import com.java12.HibernateHome.Entity.Skill;
import com.java12.HibernateHome.HibernateFactory;

import java.util.List;
import java.util.StringJoiner;

public class SkillService implements ServiceInterface<Skill> {
    private SkillDao skillDao;

    public SkillService() {
        skillDao = new SkillDao(HibernateFactory.HIBERNATE_FACTORY.getSessionFactory());

    }


    @Override
    public Skill getById(long id) {
        Skill skill = skillDao.getById(id);
        if(skill != null) return skill;
        return null;
    }

    @Override
    public String getByName(String name) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        skillDao.getAll().forEach(skill -> {
            if(skill.getProgrammingLanguage().getDescription().equalsIgnoreCase(name.trim())){
                stringJoiner.add(skill.toString());
            }
        });
        return stringJoiner.toString();
    }

    @Override
    public String createNew(Skill entity) {
        return errorType + " you cannot create new record in this table.\n Check your access rights";
    }

    @Override
    public String deleteById(long id) {
        return errorType + " incorrect access rights";
    }

    @Override
    public String update(Skill entity) {
        return errorType + "you cannot update records in this table.\n Check your access rights";
    }

    @Override
    public String getAll() {
        StringJoiner joiner = new StringJoiner("\n");
        List<Skill> list = skillDao.getAll();
        list.forEach(skill -> {
            joiner.add(skill.toString());
        });
        return joiner.toString();
    }

}
