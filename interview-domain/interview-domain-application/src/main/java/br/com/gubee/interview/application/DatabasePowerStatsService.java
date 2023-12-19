package br.com.gubee.interview.application;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.port.api.PowerStatsService;
import br.com.gubee.interview.port.spi.PowerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
