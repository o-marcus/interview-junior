package br.com.gubee.interview.port.spi.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.port.spi.comparehero.GetPowerStatsHeroPort;

import java.util.List;
import java.util.UUID;

public interface HeroRepository extends GetPowerStatsHeroPort {
    UUID create(Hero hero);
    Hero findById(UUID id);
    List<Hero> findByName(String value);
    void updateHero(UUID id, Hero hero);
    List<Hero> findAll();
    public void deleteById(UUID id);
}
