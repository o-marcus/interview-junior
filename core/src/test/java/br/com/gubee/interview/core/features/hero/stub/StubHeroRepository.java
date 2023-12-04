package br.com.gubee.interview.core.features.hero.stub;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.powerstats.stub.StubPowerStatsRepository;
import br.com.gubee.interview.model.hero.Hero;
import br.com.gubee.interview.model.powerstats.PowerStats;
import br.com.gubee.interview.model.hero.dto.JoinHeroPowerStatsByHeroNameResponse;
import br.com.gubee.interview.model.enums.Race;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class StubHeroRepository implements HeroRepository {

    private final List<Hero> heroes = new ArrayList<>();
    private final StubPowerStatsRepository stubStats;

    public StubHeroRepository(StubPowerStatsRepository stubStats) {
        this.stubStats = stubStats;
    }

    @Override
    public UUID create(Hero hero) {
        UUID id = UUID.randomUUID();
        hero.setId(id);
        heroes.add(hero);
        return id;
    }

    @Override
    public Hero findById(UUID id) {
        return heroes.stream()
                .filter(hero -> hero.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public List<Hero> findByName(String value) {
        return heroes.stream()
                .filter(hero ->  hero.getName().contains(value))
                .collect(Collectors.toList());
    }

    @Override
    public void updateHero(UUID id, Hero newHero) {
        Hero found = heroes.stream()
                .filter(hero -> hero.getId().equals(id))
                .findFirst()
                .get();
        int index = heroes.indexOf(found);
        heroes.set(index, newHero);
    }

    @Override
    public List<Hero> findAll() {
        return heroes;
    }

    @Override
    public void deleteById(UUID id) {
        heroes.removeIf(hero -> hero.getId().equals(id));
    }

    @Override
    public JoinHeroPowerStatsByHeroNameResponse findByNameJoinPowerStats(String name) {
        Hero found = findByName(name).get(0);
        PowerStats stats = stubStats.findById(found.getPowerStatsId());
        return JoinHeroPowerStatsByHeroNameResponse
                .builder()
                .id(found.getId())
                .dexterity(stats.getDexterity())
                .intelligence(stats.getIntelligence())
                .agility(stats.getAgility())
                .strength(stats.getStrength())
                .build();
    }

    public Hero createHero(String name, UUID statsId) {
        Hero hero =  Hero.builder()
                .name(name)
                .race(Race.HUMAN)
                .powerStatsId(statsId)
                .createdAt(Instant.now())
                .enabled(true)
                .build();
        create(hero);
        return hero;
    }
}
