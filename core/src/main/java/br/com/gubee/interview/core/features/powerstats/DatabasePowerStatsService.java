package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.powerstats.PowerStats;
import br.com.gubee.interview.model.powerstats.PowerStatsRepository;
import br.com.gubee.interview.model.powerstats.PowerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public class DatabasePowerStatsService implements PowerStatsService {

    @Autowired
    private PowerStatsRepository powerStatsRepository;

    public UUID create(PowerStats powerStats) {
        return powerStatsRepository.create(powerStats);
    }

    public void delete(UUID id) {
        powerStatsRepository.deleteById(id);
    }

}
