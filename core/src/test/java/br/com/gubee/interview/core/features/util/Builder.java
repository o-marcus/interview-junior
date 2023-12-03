package br.com.gubee.interview.core.features.util;

import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.hero.dto.HeroRequest;
import br.com.gubee.interview.model.hero.dto.HeroResponse;

import java.util.UUID;

public class Builder {

    public static HeroRequest createHeroRequest() {
        return HeroRequest.builder()
                .name("Batman")
                .powerStats(UUID.randomUUID())
                .race(Race.HUMAN)
                .build();
    }

}
