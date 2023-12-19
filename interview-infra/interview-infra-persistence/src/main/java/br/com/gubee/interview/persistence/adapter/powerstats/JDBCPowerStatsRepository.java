package br.com.gubee.interview.persistence.adapter.powerstats;

import br.com.gubee.interview.model.powerstats.PowerStats;
import br.com.gubee.interview.model.powerstats.PowerStatsRepository;
import br.com.gubee.interview.persistence.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
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

    private static final String FIND_ALL =
            "SELECT * FROM power_stats";

    private static final String DELETE_BY_ID =
            "DELETE FROM power_stats WHERE id = :id";


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UUID create(PowerStats powerStats) {
        try {
            var id = namedParameterJdbcTemplate.queryForObject(
                    CREATE_POWER_STATS_QUERY,
                    new BeanPropertySqlParameterSource(powerStats),
                    UUID.class);
            powerStats.setId(id);
            return id;
        }
        catch (DataAccessException exception) {
            throw new NotFoundException();
        }
    }

    public PowerStats findById(UUID id) {
        try {
            final Map<String, Object> params = Map.of("id", id);
            return namedParameterJdbcTemplate.queryForObject(
                    FIND_POWER_STATS_BY_ID,
                    params,
                    new BeanPropertyRowMapper<>(PowerStats.class)
            );
        }
        catch (DataAccessException exception) {
            throw new NotFoundException();
        }
    }

    @Override
    public List<PowerStats> findAll() {
        return namedParameterJdbcTemplate.query(
                    FIND_ALL,
                    new BeanPropertyRowMapper<>(PowerStats.class));
    }

    public void deleteById(UUID id) {
        final Map<String, Object> params = Map.of(
                "id", id
        );
        int rowsAffected = namedParameterJdbcTemplate.update(DELETE_BY_ID, params);
        if (rowsAffected == 0) {
            throw new NotFoundException("on delete id not found");
        }
    }

}
