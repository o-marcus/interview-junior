package br.com.gubee.interview.core.features.compareheroes;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.model.dto.compareheroes.CompareResponse;
import br.com.gubee.interview.model.dto.hero.HeroResponse;
import br.com.gubee.interview.model.dto.hero.JoinHeroPowerStatsByHeroNameResponse;
import br.com.gubee.interview.model.dto.powerstats.PowerStatsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompareHeroService implements CompareHeroUseCase {

    private final HeroRepository repository;

    @Override
    public CompareResponse compare(String firstHero, String secondHero) {
        JoinHeroPowerStatsByHeroNameResponse first =
                repository.findByNameJoinPowerStats(firstHero);

        JoinHeroPowerStatsByHeroNameResponse second =
                repository.findByNameJoinPowerStats(firstHero);

        return CompareResponse
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
