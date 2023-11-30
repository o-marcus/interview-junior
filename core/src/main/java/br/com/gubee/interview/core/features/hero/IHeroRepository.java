package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.dto.hero.HeroResponse;

import java.util.List;
import java.util.UUID;

public interface IHeroRepository {
    UUID create(Hero hero);
    Hero findById(UUID id);
    List<Hero> findByName(String value);
    void updateHero(UUID id, Hero hero);
    public void deleteById(UUID id);
}
