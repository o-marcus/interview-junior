package br.com.gubee.interview.core.features.hero.stub;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.dto.hero.JoinHeroPowerStatsByHeroNameResponse;

import java.util.*;

/*
*   Nesse caso em específico, foi mais trabalhoso criar essa Stub do que a funcionalidade.
* Não consegui fazer a classe com HeroRequest porque o contrato está sendo realizado com Hero.
* E acredito que o HeroRequest que veio implementado, já veio com erro. Não faz sentido incluir
* atributos de teste em uma requisição real do servidor. Por isso escolhi implementar assim.
*
* */

public class StubHeroRepository implements HeroRepository {

    private List<Hero> heroes = new ArrayList<>();
    private Map<UUID, PowerStats> map = new HashMap<>();

    @Override
    public UUID create(Hero hero) {
        UUID id = UUID.randomUUID();
        hero.setId(id);
        heroes.add(hero);
        return id;
    }

    public void addPowerStat(UUID heroID, PowerStats stats) {
        map.put(heroID, stats);
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
                .toList();
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
    public void deleteById(UUID id) {
        heroes.removeIf(hero -> hero.getId().equals(id));
    }

    public Hero findByNameEquals(String name) {
        return heroes.stream()
                .filter(hero -> hero.getName().equals(name))
                .findFirst()
                .get();
    }

    @Override
    public JoinHeroPowerStatsByHeroNameResponse findByNameJoinPowerStats(String name) {
        Hero found = findByNameEquals(name);
        PowerStats stats = map.get(found.getPowerStatsId());
        return JoinHeroPowerStatsByHeroNameResponse
                .builder()
                .id(found.getId())
                .dexterity(stats.getDexterity())
                .intelligence(stats.getIntelligence())
                .agility(stats.getAgility())
                .strength(stats.getStrength())
                .build();
    }
}
