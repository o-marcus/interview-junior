package br.com.gubee.interview.web.adapter.out.comparehero.resources;

import br.com.gubee.interview.model.CompareHero;

public class CompareMapper {

    public static CompareHeroesResponse toCompareResponse(CompareHero compare) {
        return CompareHeroesResponse.builder()
                .first(compare.getFirst())
                .second(compare.getSecond())
                .dexterity(compare.getDexterity())
                .intelligence(compare.getIntelligence())
                .agility(compare.getAgility())
                .strength(compare.getStrength())
                .build();
    }

}
