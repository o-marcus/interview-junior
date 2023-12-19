package br.com.gubee.interview.application;

import br.com.gubee.interview.port.api.CompareHeroesUseCase;
import br.com.gubee.interview.port.api.resources.CompareHeroesResponse;
import br.com.gubee.interview.port.spi.HeroRepository;
import br.com.gubee.interview.web.resources.hero.JoinHeroPowerStatsByHeroNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompareHeroesService implements CompareHeroesUseCase {

    private final HeroRepository repository;

    @Override
    public CompareHeroesResponse compare(String firstHero, String secondHero) {

        JoinHeroPowerStatsByHeroNameResponse first =
                repository.findByNameJoinPowerStats(firstHero);

        JoinHeroPowerStatsByHeroNameResponse second =
                repository.findByNameJoinPowerStats(secondHero);

        return CompareHeroesResponse
                .builder()
                .first(first.getId())
                .second(second.getId())
                .agility(first.getAgility() - second.getAgility())
                .intelligence(first.getIntelligence() - second.getIntelligence())
                .dexterity(first.getDexterity() - second.getDexterity())
                .strength(first.getStrength() - second.getStrength())
                .build();
    }

}
