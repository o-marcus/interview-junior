package br.com.gubee.interview.application;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.adapter.api.PowerStatsService;
import br.com.gubee.interview.port.spi.powerstats.PowerStatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@AllArgsConstructor
@Transactional
public class DatabasePowerStatsService implements PowerStatsService {

    @Autowired
    private final PowerStatsRepository powerStatsRepository;

    public UUID create(PowerStats powerStats) {
        return powerStatsRepository.create(powerStats);
    }

    public void delete(UUID id) {
        powerStatsRepository.deleteById(id);
    }

}
