package br.com.gubee.interview.port.spi;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.web.resources.hero.JoinHeroPowerStatsByHeroNameResponse;

import java.util.List;
import java.util.UUID;

public interface HeroRepository {
    UUID create(Hero hero);
    Hero findById(UUID id);
    List<Hero> findByName(String value);
    void updateHero(UUID id, Hero hero);
    List<Hero> findAll();
    public void deleteById(UUID id);
    JoinHeroPowerStatsByHeroNameResponse findByNameJoinPowerStats(String name);
}
