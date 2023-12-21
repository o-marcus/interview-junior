package br.com.gubee.interview.web.adapter.out;

import br.com.gubee.interview.port.spi.powerstats.PowerStatsRepository;
import br.com.gubee.interview.web.adapter.out.exception.NotFoundException;
import br.com.gubee.interview.model.PowerStats;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class JDBCPowerStatsRepository implements PowerStatsRepository {

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
            Map<String, Object> params = Map.of("id", id);
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
        Map<String, Object> params = Map.of(
                "id", id
        );
        int rowsAffected = namedParameterJdbcTemplate.update(DELETE_BY_ID, params);
        if (rowsAffected == 0) {
            throw new NotFoundException("on delete id not found");
        }
    }

    private static final String CREATE_POWER_STATS_QUERY = "INSERT INTO power_stats" +
            " (strength, agility, dexterity, intelligence)" +
            " VALUES (:strength, :agility, :dexterity, :intelligence) RETURNING id";

    private static final String FIND_POWER_STATS_BY_ID =
            "SELECT * FROM power_stats WHERE id = :id";

    private static final String FIND_ALL =
            "SELECT * FROM power_stats";

    private static final String DELETE_BY_ID =
            "DELETE FROM power_stats WHERE id = :id";


}
