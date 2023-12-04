package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.hero.Hero;
import br.com.gubee.interview.model.hero.dto.HeroRequest;
import br.com.gubee.interview.model.hero.dto.HeroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository repository;
    @Transactional
    public UUID create(Hero hero) {
        return repository.create(hero);
    }

    public Hero findById(UUID id) {
        Hero hero = repository.findById(id);
        return hero;
    }

    public List<Hero> findHeroByName(String name) {
        return repository.findByName(name);
    }

    public void update(UUID id, Hero hero) {
        repository.updateHero(id, hero);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}
