package app.domain.entity;




public enum ClassEnum {
    WARRIOR ("Warrior"),
    ARCHER ("Archer"),
    MAGE ("Mage");

    private final String heroClass;

    ClassEnum(String heroClass) {
        this.heroClass = heroClass;
    }

    public String getHeroClass() {
        return heroClass;
    }


}
