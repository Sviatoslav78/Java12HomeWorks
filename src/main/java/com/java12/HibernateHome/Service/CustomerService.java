package com.java12.HibernateHome.Service;

import com.java12.HibernateHome.Dao.CustomerDao;
import com.java12.HibernateHome.Entity.Customer;
import com.java12.HibernateHome.HibernateFactory;

import java.util.List;
import java.util.StringJoiner;

public class CustomerService implements ServiceInterface<Customer> {
    private CustomerDao customerDao;

    public CustomerService() {
        this.customerDao = new CustomerDao(HibernateFactory.HIBERNATE_FACTORY.getSessionFactory());
    }

    @Override
    public Customer getById(long id) {
        Customer customer = customerDao.getById(id);
        if(customer != null){
            return customer;
        }
        return null;
    }

    @Override
    public String getByName(String name) {
        List<Customer> customerList = customerDao.getAll();
        StringJoiner joiner = new StringJoiner("\n");
        customerList.forEach(customer -> {
            if(customer.getCustomerName().equalsIgnoreCase(name)){
                joiner.add(customer.toString());
            }
        });
        return joiner.toString();
    }

    @Override
    public String createNew(Customer entity) {
        return customerDao.addToTable(entity);
    }

    @Override
    public String deleteById(long id) {
        return deleteById(id);
    }

    @Override
    public String update(Customer entity) {
        return customerDao.update(entity);
    }

    @Override
    public String getAll() {
        List<Customer> all = customerDao.getAll();
        StringJoiner joiner = new StringJoiner("\n");
        all.forEach(customer -> joiner.add(customer.toString()));
        return joiner.toString();
    }
}
