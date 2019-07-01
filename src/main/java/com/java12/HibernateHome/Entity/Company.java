package com.java12.HibernateHome.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "id_tax")
    private long taxId;

    @Override
    public String toString() {
        return "COMPANY: " +
                " ID " + id +
                " COMPANY NAME '" + companyName + '\'' +
                " TAX ID" + taxId +
                " DEVELOPERS: " + Arrays.toString(developerSet.toArray()) +
                " PROJECTS: " + Arrays.toString(projects.toArray()) +
                ';';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Company(long id, String companyName, long taxId) {
        this.id = id;
        this.companyName = companyName;
        this.taxId = taxId;
    }

    @ManyToMany
    @JoinTable(name = "company_developers",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<Developer> developerSet = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Project> projects = new HashSet<>();

    public void addProject(Project project){
        projects.add(project);
    }

    public void addDeveloper(Developer developer){
        developerSet.add(developer);
    }
}
