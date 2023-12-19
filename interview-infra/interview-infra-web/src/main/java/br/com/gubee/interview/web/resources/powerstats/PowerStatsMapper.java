package br.com.gubee.interview.web.resources.powerstats;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.web.resources.powerstats.PowerStatsRequest;
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
