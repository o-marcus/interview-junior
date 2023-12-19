package br.com.gubee.interview.web.resources.hero;


import br.com.gubee.interview.model.Hero;

public class HeroMapper {

    public static HeroResponse toHeroResponse(Hero hero) {
        return HeroResponse.builder()
                .name(hero.getName())
                .powerStatsId(hero.getPowerStatsId())
                .race(hero.getRace())
                .updatedAt(hero.getUpdatedAt())
                .createdAt(hero.getCreatedAt())
                .enabled(hero.isEnabled())
                .build();
    }

    public static Hero toHero(HeroRequest heroRequest) {
        return Hero.builder()
                .name(heroRequest.getName())
                .race(heroRequest.getRace())
                .powerStatsId(heroRequest.getStats())
                .enabled(true)
                .build();
    }

}
