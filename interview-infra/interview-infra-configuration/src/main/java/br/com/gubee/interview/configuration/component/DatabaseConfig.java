package br.com.gubee.interview.configuration.component;

import br.com.gubee.interview.persistence.adapter.hero.JDBCHeroRepository;
import br.com.gubee.interview.persistence.adapter.powerstats.JDBCPowerStatsRepository;
import br.com.gubee.interview.port.spi.hero.HeroRepository;
import br.com.gubee.interview.port.spi.powerstats.PowerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Bean
    public PowerStatsRepository powerStatsRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new JDBCPowerStatsRepository(template);
    }

    @Bean
    public HeroRepository heroRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new JDBCHeroRepository(template);
    }

}
