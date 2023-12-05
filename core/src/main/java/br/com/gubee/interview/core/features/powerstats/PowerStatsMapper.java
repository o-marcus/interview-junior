package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.powerstats.PowerStats;
import br.com.gubee.interview.model.powerstats.dto.PowerStatsRequest;

public class PowerStatsMapper {

    public static PowerStats toPowerStats(PowerStatsRequest powerStats) {
        return  PowerStats.builder()
                .intelligence(powerStats.getIntelligence())
                .dexterity(powerStats.getDexterity())
                .agility(powerStats.getAgility())
                .strength(powerStats.getStrength())
                .build();
    }

}
