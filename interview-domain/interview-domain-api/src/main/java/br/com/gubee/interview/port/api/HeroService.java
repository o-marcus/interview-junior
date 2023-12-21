package br.com.gubee.interview.port.api;

import br.com.gubee.interview.model.Hero;
import java.util.List;
import java.util.UUID;

public interface HeroService {
    UUID create(Hero hero);
    Hero findHero(UUID id);
    List<Hero> findHero(String name);
    void updateHero(UUID id, Hero hero);
    void deleteHero(UUID id);

}
