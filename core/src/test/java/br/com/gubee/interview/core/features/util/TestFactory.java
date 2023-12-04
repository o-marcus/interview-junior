package br.com.gubee.interview.core.features.util;

import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.hero.Hero;
import br.com.gubee.interview.model.hero.dto.HeroRequest;
import br.com.gubee.interview.model.hero.dto.HeroResponse;
import br.com.gubee.interview.model.powerstats.PowerStats;
import br.com.gubee.interview.model.powerstats.dto.PowerStatsRequest;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class TestFactory {
    private static final String Name = "Flash Multiverso#";
    private static final Random random = new Random();

    public static HeroRequest createHeroRequest() {
        return HeroRequest.builder()
                .name(Name + random.nextInt())
                .stats(UUID.randomUUID())
                .race(Race.HUMAN)
                .build();
    }

    public static HeroRequest createHeroRequest(UUID id) {
        return HeroRequest.builder()
                .name(Name + random.nextInt())
                .race(Race.HUMAN)
                .stats(id)
                .build();
    }

    public static HeroResponse createHeroResponse() {
        return HeroResponse.builder()
                .name(Name+random.nextInt())
                .race(Race.HUMAN)
                .powerStatsId(UUID.randomUUID())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .enabled(true)
                .build();
    }

    public static Hero createHero() {
        return Hero.builder()
                .name(Name + random.nextInt())
                .powerStatsId(UUID.randomUUID())
                .race(Race.HUMAN)
                .enabled(true)
                .build();
    }

    public static Hero createHero(UUID id) {
        return Hero.builder()
                .name(Name + random.nextInt())
                .race(Race.HUMAN)
                .powerStatsId(id)
                .enabled(true)
                .build();
    }


    public static PowerStatsRequest createPowerStatsRequest() {
        return PowerStatsRequest.builder()
                .agility(8)
                .intelligence(8)
                .dexterity(8)
                .strength(8)
                .build();
    }

    public static PowerStats createPowerStats() {
        return PowerStats.builder()
                .agility(8)
                .intelligence(8)
                .dexterity(8)
                .strength(8)
                .build();
    }


}
