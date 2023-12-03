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
    public UUID create(HeroRequest heroDto) {
        return repository.create(new Hero(heroDto));
    }

    public HeroResponse findById(UUID id) {
        Hero hero = repository.findById(id);
        return toHeroResponse(hero);
    }

    public List<HeroResponse> findHeroByName(String name) {
        return repository
                .findByName(name)
                .stream()
                .map(HeroService::toHeroResponse)
                .collect(Collectors.toList());
    }

    public void update(UUID id, HeroRequest request) {
        repository.updateHero(id, new Hero(request));
    }

    public static HeroResponse toHeroResponse(Hero hero) {
         return HeroResponse.builder()
                .name(hero.getName())
                .powerStatsId(hero.getPowerStatsId())
                .race(hero.getRace())
                .updatedAt(hero.getUpdatedAt())
                .createdAt(hero.getCreatedAt())
                .enabled(hero.isEnabled())
                .build();
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
