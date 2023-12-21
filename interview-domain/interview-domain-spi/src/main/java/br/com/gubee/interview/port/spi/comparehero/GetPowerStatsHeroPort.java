package br.com.gubee.interview.port.spi.comparehero;

import br.com.gubee.interview.port.spi.entities.HeroPowerStats;

public interface GetPowerStatsHeroPort {
    HeroPowerStats findByNameJoinPowerStats(String name);
}
