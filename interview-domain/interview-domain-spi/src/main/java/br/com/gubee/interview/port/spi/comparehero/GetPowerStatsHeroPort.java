package br.com.gubee.interview.port.spi.comparehero;

import br.com.gubee.interview.port.spi.resources.JoinHeroPowerStatsByHeroName;

public interface GetPowerStatsHeroPort {
    JoinHeroPowerStatsByHeroName findByNameJoinPowerStats(String name);
}
