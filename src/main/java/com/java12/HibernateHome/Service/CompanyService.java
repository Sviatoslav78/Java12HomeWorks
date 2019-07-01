package com.java12.HibernateHome.Service;

import com.java12.HibernateHome.Dao.CompanyDao;
import com.java12.HibernateHome.Entity.Company;
import com.java12.HibernateHome.HibernateFactory;

import java.util.List;
import java.util.StringJoiner;

public class CompanyService implements ServiceInterface<Company> {
    private CompanyDao companyDao;

    public CompanyService() {
        this.companyDao = new CompanyDao(HibernateFactory.HIBERNATE_FACTORY.getSessionFactory());
    }

    @Override
    public Company getById(long id) {
        Company company = companyDao.getById(id);
        if (company != null) {
            return company;
        }

        return null;
    }

    @Override
    public String getByName(String name) {
        List<Company> all = companyDao.getAll();
        StringJoiner joiner = new StringJoiner("\n");
        all.forEach(company -> {
            if (company.getCompanyName().equalsIgnoreCase(name)) {
                joiner.add(company.toString());
            }
        });
        return joiner.toString();
    }


    @Override
    public String createNew(Company company) {

        companyDao.addToTable(company);
        return infoType + " company just been added to table";
    }


    @Override
    public String deleteById(long id) {
        return companyDao.deleteById(id);
    }

    @Override
    public String update(Company company) {
        companyDao.update(company);
        return infoType + " company just been updated";
    }


    @Override
    public String getAll() {
        List<Company> all = companyDao.getAll();
        StringJoiner joiner = new StringJoiner("\n");
        all.forEach(company -> joiner.add(company.toString()));
        return joiner.toString();
    }

}
