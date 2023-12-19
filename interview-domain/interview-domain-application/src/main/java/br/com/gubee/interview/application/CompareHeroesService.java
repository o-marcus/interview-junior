package br.com.gubee.interview.application;

import br.com.gubee.interview.adapter.api.CompareHeroesUseCase;
import br.com.gubee.interview.adapter.api.resources.CompareHeroesResponse;
import br.com.gubee.interview.port.spi.comparehero.GetPowerStatsHeroPort;
import br.com.gubee.interview.port.spi.resources.JoinHeroPowerStatsByHeroName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompareHeroesService implements CompareHeroesUseCase {

    private final GetPowerStatsHeroPort repository;

    @Override
    public CompareHeroesResponse compare(String firstHero, String secondHero) {

        JoinHeroPowerStatsByHeroName first =
                repository.findByNameJoinPowerStats(firstHero);

        JoinHeroPowerStatsByHeroName second =
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
