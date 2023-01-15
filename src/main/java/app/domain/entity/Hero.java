package app.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Hero  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String name;

    @Column(name = "class")
    @Enumerated
    private ClassEnum heroClass;

    private int level;


    public Hero() {
    }

    public Hero(String name, ClassEnum heroClass, int level ) {
        this.name = name;
        this.heroClass = heroClass;
        this.level = level;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassEnum getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(ClassEnum clazz) {
        this.heroClass = clazz;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
