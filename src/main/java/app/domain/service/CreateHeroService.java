package app.domain.service;

public class CreateHeroService {

private String name;

private String heroClass;

private int level;

    public CreateHeroService(String name, String heroClass, int level) {
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

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
