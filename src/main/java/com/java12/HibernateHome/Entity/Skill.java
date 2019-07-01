package com.java12.HibernateHome.Entity;

import com.java12.HibernateHome.Entity.SkillEnum.ProgrammingLanguage;
import com.java12.HibernateHome.Entity.SkillEnum.SkillLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "skills")
@Data
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "programming_language")
    ProgrammingLanguage programmingLanguage;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    SkillLevel skillsLevel;


    public Skill(long id, ProgrammingLanguage programmingLanguage, SkillLevel skillsLevel) {
        this.id = id;
        this.programmingLanguage = programmingLanguage;
        this.skillsLevel = skillsLevel;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "developer_skill",
            joinColumns = @JoinColumn(name = "skill_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<Developer> developers = new HashSet<Developer>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return id == skill.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
