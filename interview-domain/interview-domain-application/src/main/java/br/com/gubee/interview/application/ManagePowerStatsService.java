package br.com.gubee.interview.application;

import br.com.gubee.interview.hexarchitecture.UseCase;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.port.api.PowerStatsService;
import br.com.gubee.interview.port.spi.powerstats.PowerStatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@UseCase
@AllArgsConstructor
@Transactional
public class ManagePowerStatsService implements PowerStatsService {

    @Autowired
    private final PowerStatsRepository powerStatsRepository;

    public UUID create(PowerStats powerStats) {
        return powerStatsRepository.create(powerStats);
    }

    public void delete(UUID id) {
        powerStatsRepository.deleteById(id);
    }

}
