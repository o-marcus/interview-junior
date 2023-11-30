package br.com.gubee.interview.core.features.compareheroes;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.model.dto.compareheroes.CompareResponse;
import br.com.gubee.interview.model.dto.hero.HeroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompareHeroService implements CompareHeroUseCase {

    private final PowerStatsRepository repository;

    @Override
    public CompareResponse compare(String firstHero, String secondHero) {
        return null;
    }

    private HeroResponse subtract(HeroResponse first, HeroResponse second) {
        return null;
    }

}
