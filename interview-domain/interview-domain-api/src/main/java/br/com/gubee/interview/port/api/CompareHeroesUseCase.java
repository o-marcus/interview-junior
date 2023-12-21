package br.com.gubee.interview.port.api;


import br.com.gubee.interview.model.CompareHero;

public interface CompareHeroesUseCase {
    CompareHero compare (String firstHero, String secondHero);
}
