package br.com.gubee.interview.configuration;

import br.com.gubee.interview.port.api.HeroService;
import br.com.gubee.interview.port.api.PowerStatsService;
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
