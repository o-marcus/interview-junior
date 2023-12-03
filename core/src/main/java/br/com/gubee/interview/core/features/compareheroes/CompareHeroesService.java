package br.com.gubee.interview.core.features.compareheroes;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.model.compareheroes.CompareHeroesResponse;
import br.com.gubee.interview.model.hero.dto.JoinHeroPowerStatsByHeroNameResponse;
import br.com.gubee.interview.model.powerstats.dto.PowerStatsResponse;
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
                .difference(subtract(first, second))
                .build();
    }

    private PowerStatsResponse subtract(
            JoinHeroPowerStatsByHeroNameResponse firstPower,
            JoinHeroPowerStatsByHeroNameResponse secondPower
    ) {
        return PowerStatsResponse.builder()
                .agility(firstPower.getAgility() - secondPower.getAgility())
                .intelligence(firstPower.getIntelligence() - secondPower.getIntelligence())
                .dexterity(firstPower.getDexterity() - secondPower.getDexterity())
                .strength(firstPower.getStrength() - secondPower.getStrength())
                .build();
    }

}
