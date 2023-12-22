package br.com.gubee.interview.application;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.port.spi.stub.StubHeroRepository;
import br.com.gubee.interview.port.spi.stub.StubPowerStatsRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CompareHeroesServiceTest {

    StubPowerStatsRepository stubStats = new StubPowerStatsRepository();
    StubHeroRepository stubHero = new StubHeroRepository(stubStats);
    CompareHeroesService service = new CompareHeroesService(stubHero);

    @Test
    void shouldSubtractHeroPowerStatsWhenPowerStatsIsDifferent() {
        // given
        PowerStats s1 = stubStats.createStats(7, 10, 6, 2);
        PowerStats s2 = stubStats.createStats(7, 10, 10, 8);
        Hero batman = stubHero.createHero("Batman", s1.getId());
        Hero flash = stubHero.createHero("Flash", s2.getId());

        // when
        var difference  = service.compare(batman.getName(), flash.getName());

        // then
        assertEquals(difference.getFirst(), batman.getId());
        assertEquals(difference.getSecond(), flash.getId());
        assertEquals(difference.getAgility(), s1.getAgility() - s2.getAgility());
        assertEquals(difference.getStrength(), s1.getStrength() - s2.getStrength());
        assertEquals(difference.getDexterity(), s1.getDexterity() - s2.getDexterity());
        assertEquals(difference.getIntelligence(), s1.getIntelligence() - s2.getIntelligence());
    }


    @Test
    void shouldSubtractHeroPowerStatsWhenPowerStatsIsSame() {
        // given
        PowerStats s1 = stubStats.createStats(7, 10, 6, 2);
        Hero batman = stubHero.createHero("Batman", s1.getId());
        Hero flash = stubHero.createHero("Flash", s1.getId());

        // when
        var difference = service.compare(batman.getName(), flash.getName());

        // then
        assertEquals(difference.getFirst(), batman.getId());
        assertEquals(difference.getSecond(), flash.getId());
        assertEquals(difference.getAgility(), 0);
        assertEquals(difference.getStrength(),0);
        assertEquals(difference.getDexterity(), 0);
        assertEquals(difference.getIntelligence(),0);
    }

}