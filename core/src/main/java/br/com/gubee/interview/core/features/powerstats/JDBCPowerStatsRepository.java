package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.powerstats.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JDBCPowerStatsRepository implements PowerStatsRepository {

    private static final String CREATE_POWER_STATS_QUERY = "INSERT INTO power_stats" +
        " (strength, agility, dexterity, intelligence)" +
        " VALUES (:strength, :agility, :dexterity, :intelligence) RETURNING id";

    private static final String FIND_POWER_STATS_BY_ID =
            "SELECT * FROM power_stats WHERE id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UUID create(PowerStats powerStats) {
        return namedParameterJdbcTemplate.queryForObject(
            CREATE_POWER_STATS_QUERY,
            new BeanPropertySqlParameterSource(powerStats),
            UUID.class);
    }

    public PowerStats findById(UUID id) {
        final Map<String, Object> params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                FIND_POWER_STATS_BY_ID,
                params,
                new BeanPropertyRowMapper<>(PowerStats.class)
        );
    }

}
