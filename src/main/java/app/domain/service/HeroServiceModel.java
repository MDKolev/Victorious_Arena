package app.domain.service;

import app.domain.entity.ClassEnum;

import javax.persistence.Column;
import javax.persistence.Enumerated;

public class HeroServiceModel {

    private Long id;

    private String name;

    private ClassEnum heroClass;

    private int level;

    public HeroServiceModel() {
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

    public void setHeroClass(ClassEnum heroClass) {
        this.heroClass = heroClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
