package app.service;

import app.domain.entity.Hero;

import java.util.List;

public interface HeroServiceInt {
    List<Hero> getAllHeroes();

    void saveHero(Hero hero);

    Hero getHeroById(long id);

    void deleteHeroById(long id);
}
