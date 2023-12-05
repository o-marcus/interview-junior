package br.com.gubee.interview.model.powerstats.stub;

import br.com.gubee.interview.model.powerstats.PowerStats;
import br.com.gubee.interview.model.powerstats.PowerStatsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StubPowerStatsRepository
        implements PowerStatsRepository {

    List<PowerStats> stats = new ArrayList<>();

    public UUID create(PowerStats powerStats) {
        UUID uuid = UUID.randomUUID();
        powerStats.setId(uuid);
        stats.add(powerStats);
        return uuid;
    }

    @Override
    public PowerStats findById(UUID id) {
        return stats.stream()
                .filter(stats -> stats.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public List<PowerStats> findAll() {
        return stats;
    }

    @Override
    public void deleteById(UUID id) {
        stats.removeIf(stat -> stat.getId().equals(id));
    }

    public PowerStats createStats(int dextery, int intelligence, int agility, int strength) {
        PowerStats stat = PowerStats
                .builder()
                .dexterity(dextery)
                .intelligence(intelligence)
                .agility(agility)
                .strength(strength)
                .build();
        create(stat);
        return stat;
    }

}
