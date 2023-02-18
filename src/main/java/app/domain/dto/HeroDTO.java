package app.domain.dto;

import app.domain.entity.ClassEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class HeroDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Column(name = "class")
    private ClassEnum heroClass;

    private int level;

    public HeroDTO() {}

    public HeroDTO(String name, ClassEnum heroClass, int level) {
        this.name = name;
        this.heroClass = heroClass;
        this.level = level;
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
