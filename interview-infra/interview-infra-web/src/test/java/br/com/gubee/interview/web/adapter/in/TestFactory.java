package br.com.gubee.interview.web.adapter.in;

import br.com.gubee.interview.enums.Race;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.web.adapter.out.hero.resources.HeroRequest;
import br.com.gubee.interview.web.adapter.out.powerstats.resources.PowerStatsRequest;

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

    public static HeroRequest createHeroRequest(String name, UUID id) {
        return HeroRequest.builder()
                .name(name)
                .race(Race.HUMAN)
                .stats(id)
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

    public static PowerStatsRequest createPowerStatsRequest() {
        return PowerStatsRequest.builder()
                .agility(random.nextInt(10))
                .intelligence(random.nextInt( 10))
                .dexterity(random.nextInt( 10))
                .strength(random.nextInt( 10))
                .build();
    }

    public static PowerStats createPowerStats() {
        return PowerStats.builder()
                .agility(random.nextInt(10))
                .intelligence(random.nextInt( 10))
                .dexterity(random.nextInt( 10))
                .strength(random.nextInt( 10))
                .build();
    }

}
