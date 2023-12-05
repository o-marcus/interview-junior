package br.com.gubee.interview.model.powerstats;

import br.com.gubee.interview.model.powerstats.PowerStats;

import java.util.UUID;

public interface PowerStatsService {
    UUID create(PowerStats powerStats);
    void delete(UUID id);

}
