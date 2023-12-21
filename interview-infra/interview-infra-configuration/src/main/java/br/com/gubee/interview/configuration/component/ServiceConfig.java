package br.com.gubee.interview.configuration.component;

import br.com.gubee.interview.port.api.CompareHeroesUseCase;
import br.com.gubee.interview.application.CompareHeroesService;
import br.com.gubee.interview.application.DatabaseHeroService;
import br.com.gubee.interview.application.DatabasePowerStatsService;
import br.com.gubee.interview.port.api.HeroService;
import br.com.gubee.interview.port.api.PowerStatsService;
import br.com.gubee.interview.port.spi.hero.HeroRepository;
import br.com.gubee.interview.port.spi.powerstats.PowerStatsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public HeroService heroService(HeroRepository repository) {
        return new DatabaseHeroService(repository);
    }

    @Bean
    public CompareHeroesUseCase compareHeroesService(HeroRepository repository) {
        return new CompareHeroesService(repository);
    }

    @Bean
    public PowerStatsService powerStatsService(PowerStatsRepository repository) {
        return new DatabasePowerStatsService(repository);
    }

}
