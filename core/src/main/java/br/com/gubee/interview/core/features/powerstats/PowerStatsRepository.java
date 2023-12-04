package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.powerstats.PowerStats;

import java.util.List;
import java.util.UUID;

public interface PowerStatsRepository {

    UUID create(PowerStats powerStats);
    PowerStats findById(UUID id);
    List<PowerStats> findAll();
    void deleteById(UUID id);

}
