package br.com.gubee.interview.application;

import br.com.gubee.interview.model.CompareHero;
import br.com.gubee.interview.port.api.CompareHeroesUseCase;
import br.com.gubee.interview.port.spi.comparehero.GetPowerStatsHeroPort;
import br.com.gubee.interview.port.spi.entities.HeroPowerStats;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompareHeroesService implements CompareHeroesUseCase {

    private final GetPowerStatsHeroPort repository;

    @Override
    public CompareHero compare(String firstHero, String secondHero) {

        HeroPowerStats first =
                repository.findByNameJoinPowerStats(firstHero);

        HeroPowerStats second =
                repository.findByNameJoinPowerStats(secondHero);

        return CompareHero
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
