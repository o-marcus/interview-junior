package br.com.gubee.interview.application;

import br.com.gubee.interview.hexarchitecture.UseCase;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.port.api.HeroService;
import br.com.gubee.interview.port.spi.hero.HeroRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@UseCase
@AllArgsConstructor
public class ManageHeroService implements HeroService {

    private final HeroRepository repository;

    public UUID create(Hero hero) {
        return repository.create(hero);
    }

    @Override
    public Hero findHero(UUID id) {
        Hero hero = repository.findById(id);
        return hero;
    }

    @Override
    public List<Hero> findHero(String name) {
        return repository.findByName(name);
    }

    @Override
    public void updateHero(UUID id, Hero hero) {
        repository.updateHero(id, hero);
    }

    @Override
    public void deleteHero(UUID id) {
        repository.deleteById(id);
    }

}
