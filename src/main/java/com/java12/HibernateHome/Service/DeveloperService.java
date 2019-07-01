package com.java12.HibernateHome.Service;

import com.java12.HibernateHome.Dao.DeveloperDao;
import com.java12.HibernateHome.Entity.Developer;
import com.java12.HibernateHome.HibernateFactory;

import java.util.List;
import java.util.StringJoiner;

public class DeveloperService implements ServiceInterface<Developer> {
    private DeveloperDao developerDao;

    public DeveloperService() {
        developerDao = new DeveloperDao(HibernateFactory.HIBERNATE_FACTORY.getSessionFactory());

    }

    @Override
    public Developer getById(long id) {
        Developer developer = developerDao.getById(id);
        if (developer != null) {
            return developer;
        }
        return null;
    }

    @Override
    public String getByName(String name) {
        List<Developer> all = developerDao.getAll();
        StringJoiner joiner = new StringJoiner("\n");
        all.forEach(developer -> {
            if (name.contains(developer.getFirstName().toLowerCase()) &&
                    name.contains(developer.getLastName().toLowerCase())) {
                joiner.add(developer.toString());
            }
        });
        return joiner.toString();
    }



    @Override
    public String createNew(Developer entity) {
        return developerDao.addToTable(entity);
    }

    @Override
    public String deleteById(long id) {
        return developerDao.deleteById(id);
    }

    @Override
    public String update(Developer entity) {
        return developerDao.update(entity);
    }

    @Override
    public String getAll() {
        List<Developer> all = developerDao.getAll();
        StringJoiner joiner = new StringJoiner("\n");
        all.forEach(developer -> {
            joiner.add(developer.toString());
        });
        return joiner.toString();
    }

}
