package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.powerstats.PowerStats;
import br.com.gubee.interview.model.powerstats.dto.PowerStatsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerStatsService {

    private final PowerStatsRepository powerStatsRepository;

    @Transactional
    public UUID create(PowerStatsRequest powerStats) {
        return powerStatsRepository.create(map(powerStats));
    }

    @Transactional
    public void deleteById(UUID id) {
        powerStatsRepository.deleteById(id);
    }

    public static PowerStats map(PowerStatsRequest powerStats) {
        return  PowerStats.builder()
                .intelligence(powerStats.getIntelligence())
                .dexterity(powerStats.getDexterity())
                .agility(powerStats.getAgility())
                .strength(powerStats.getStrength())
                .build();
    }
}
