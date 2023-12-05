package br.com.gubee.interview.core.features.di;

import br.com.gubee.interview.core.features.hero.DatabaseHeroService;
import br.com.gubee.interview.core.features.powerstats.DatabasePowerStatsService;
import br.com.gubee.interview.model.hero.HeroService;
import br.com.gubee.interview.model.powerstats.PowerStatsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public HeroService heroService() {
        return new DatabaseHeroService();
    }

    @Bean
    public PowerStatsService powerStatsService() {
        return new DatabasePowerStatsService();
    }
}
