package com.java12.HibernateHome.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Table(name = "customers")
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customer_name")
    private String customerName;

    public Customer(long id, String customerName, String extraInfo) {
        this.id = id;
        this.customerName = customerName;
        this.extraInfo = extraInfo;
    }

    @Column(name = "extra_info")
    private String extraInfo;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Project> projectSet = new HashSet<>();

    public void addProject(Project project){
        projectSet.add(project);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer " +
                "ID " + id +
                "CUSTOMER NAME: " + customerName + '\'' +
                "EXTRA INFO" + extraInfo + '\'' +
                "PROJECTS: " + Arrays.toString(projectSet.toArray()) +
                ';';
    }
}
