package com.java12.HibernateHome.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "projects")
@Data
@Entity
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private long id;

    @Column(name = "project_name")
    private String name;

    @Column(name = "start_date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "developer_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<Developer> developers = new HashSet<>();

    public Project(long id, String name, String  date, Customer customer, Company company) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.customer = customer;
        this.company = company;
    }

    public void addDeveloper(Developer developer){
        developers.add(developer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PROJECT " +
                " ID " + id +
                ", NAME: " + name +
                ", DATE: " + date +
                ", CUSTOMER: " + customer +
                ", COMPANY: " + company +
                ", DEVELOPERS: " + Arrays.toString(developers.toArray()) +
                ';';
    }
}
