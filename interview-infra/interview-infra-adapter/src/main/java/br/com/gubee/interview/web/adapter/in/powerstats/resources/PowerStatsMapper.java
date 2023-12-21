package br.com.gubee.interview.web.adapter.in.powerstats.resources;

import br.com.gubee.interview.model.PowerStats;

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
