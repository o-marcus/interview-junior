package br.com.gubee.interview.port.api;

import br.com.gubee.interview.model.PowerStats;

import java.util.UUID;

public interface PowerStatsService {
    UUID create(PowerStats powerStats);
    void delete(UUID id);
}
