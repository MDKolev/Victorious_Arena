package app.domain.dto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class HeroDTO {


    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Column(name = "class")
    private String clazz;

    @Positive
    private int level;

    public HeroDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}
