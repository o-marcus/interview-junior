package br.com.gubee.interview.core.features.compareheroes;

import br.com.gubee.interview.model.dto.compareheroes.CompareResponse;

public interface CompareHeroUseCase {
    CompareResponse compare (String firstHero, String secondHero);
}
