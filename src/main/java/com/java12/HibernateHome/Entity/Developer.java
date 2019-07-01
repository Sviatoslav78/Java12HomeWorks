package com.java12.HibernateHome.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "developers")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Developer(long id, String firstName, String lastName, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }


    @Column(name = "salary")
    private double salary;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "developer_projects",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "developer_skill",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skillSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DEVELOPER" +
                " ID " + id +
                " FIRST NAME " + firstName + '\'' +
                ", LAST NAME " + lastName + '\'' +
                ", SALARY: " + salary +
                ", PROJECTS: " + Arrays.toString(projects.toArray()) +
                ", SKILLS: " + Arrays.toString(skillSet.toArray()) +
                ';';
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void addSkill(Skill skill) {
        skillSet.add(skill);
    }
}
