package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.NotFoundException;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.dto.hero.JoinHeroPowerStatsByHeroNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@Primary
@RequiredArgsConstructor
public class JDBCHeroRepository implements HeroRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UUID create(Hero hero) {
        try {
            final Map<String, Object> params = Map.of("name", hero.getName(),
                    "race", hero.getRace().name(),
                    "powerStatsId", hero.getPowerStatsId());

            return namedParameterJdbcTemplate.queryForObject(
                    CREATE_HERO_QUERY,
                    params,
                    UUID.class);
        } catch (DataIntegrityViolationException e) {
            throw new NotFoundException("on create foreign key power_stats_id not found");
        }
    }

    public Hero findById(UUID id) {
        try {
            final Map<String, Object> params = Map.of("id", id);
            return namedParameterJdbcTemplate.queryForObject(
                    FIND_HERO_BY_ID,
                    params,
                    new BeanPropertyRowMapper<>(Hero.class));
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("on findById id not found");
        }
    }

    public JoinHeroPowerStatsByHeroNameResponse findByNameJoinPowerStats(String name) {
        try {
            final Map<String, Object> params = Map.of("name", name);
            return namedParameterJdbcTemplate.queryForObject(
                    JOIN_POWER_STATS_BY_NAME,
                    params,
                    new BeanPropertyRowMapper<>(JoinHeroPowerStatsByHeroNameResponse.class));
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("on findById id not found");
        }
    }

    public List<Hero> findByName(String value) {
        final Map<String, Object> params = Map.of("value", "%" + value + "%");
        return namedParameterJdbcTemplate.query(
                FIND_HERO_BY_NAME,
                params,
                new BeanPropertyRowMapper<>(Hero.class)
        );
    }


    public void updateHero(UUID id, Hero hero) {
        final Map<String, Object> params = Map.of(
                "heroId", id,
                "newName", hero.getName(),
                "newRace", hero.getRace().name(),
                "newPowerStatsId", hero.getPowerStatsId()
        );

        int rowsAffected = namedParameterJdbcTemplate.update(UPDATE_HERO, params);
        if (rowsAffected == 0) {
            throw new NotFoundException("on update id not found");
        }
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

    private static final String CREATE_HERO_QUERY = "INSERT INTO hero" +
            " (name, race, power_stats_id)" +
            " VALUES (:name, :race, :powerStatsId) RETURNING id";

    private static final String FIND_HERO_BY_ID =
            "SELECT * FROM hero WHERE id = :id";

    private static final String FIND_HERO_BY_NAME =
            "SELECT * FROM hero WHERE name ILIKE :value";

    private static final String JOIN_POWER_STATS_BY_NAME =
            "SELECT h.id, ps.strength, ps.agility, ps.dexterity, ps.intelligence " +
                    "FROM hero h " +
                    "JOIN power_stats ps ON h.power_stats_id = ps.id " +
                    "WHERE h.name = :name";

    private static final String DELETE_BY_ID =
            "DELETE FROM hero WHERE id = :id";

    private static final String UPDATE_HERO = "UPDATE hero " +
            "SET " +
            "    name = :newName," +
            "    race = :newRace," +
            "    power_stats_id = :newPowerStatsId WHERE id = :heroId;";

}
