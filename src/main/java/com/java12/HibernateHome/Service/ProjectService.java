package com.java12.HibernateHome.Service;

import com.java12.HibernateHome.Dao.ProjectDao;
import com.java12.HibernateHome.Entity.Project;
import com.java12.HibernateHome.HibernateFactory;

import java.util.List;
import java.util.StringJoiner;

public class ProjectService implements ServiceInterface<Project> {

    private ProjectDao projectDao;

    public ProjectService() {
        this.projectDao = new ProjectDao(HibernateFactory.HIBERNATE_FACTORY.getSessionFactory());
    }

    @Override
    public Project getById(long id) {
        Project project = projectDao.getById(id);
        if (project != null) {
            return project;
        }
        return null;
    }

    @Override
    public String getByName(String name) {
        List<Project> all = projectDao.getAll();
        StringJoiner joiner = new StringJoiner("\n");
        all.forEach(project -> {
            if (project.getName().equalsIgnoreCase(name)) {
                joiner.add(project.toString());
            }
        });
        return joiner.toString();
    }

    @Override
    public String createNew(Project entity) {
        projectDao.addToTable(entity);
        return infoType + " project just been created";
    }

    @Override
    public String deleteById(long id) {
        return projectDao.deleteById(id);
    }

    @Override
    public String update(Project entity) {
        projectDao.update(entity);
        return infoType + " project just been updated";
    }

    @Override
    public String getAll() {
        List<Project> getAll = projectDao.getAll();
        StringJoiner joiner = new StringJoiner("\n");

        getAll.forEach(project -> joiner.add(project.toString()));

        return joiner.toString();
    }
}
