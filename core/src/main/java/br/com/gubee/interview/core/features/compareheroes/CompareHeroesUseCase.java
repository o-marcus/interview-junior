package br.com.gubee.interview.core.features.compareheroes;
import br.com.gubee.interview.model.compareheroes.CompareHeroesResponse;

public interface CompareHeroesUseCase {
    CompareHeroesResponse compare (String firstHero, String secondHero);
}
