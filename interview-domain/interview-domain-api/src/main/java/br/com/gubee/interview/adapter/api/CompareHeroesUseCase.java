package br.com.gubee.interview.adapter.api;

import br.com.gubee.interview.adapter.api.resources.CompareHeroesResponse;

public interface CompareHeroesUseCase {
    CompareHeroesResponse compare (String firstHero, String secondHero);
}
