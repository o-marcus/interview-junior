package br.com.gubee.interview.port.api;

import br.com.gubee.interview.port.api.resources.CompareHeroesResponse;

public interface CompareHeroesUseCase {
    CompareHeroesResponse compare (String firstHero, String secondHero);
}
