package br.com.gubee.interview.web.adapter.in.hero.comparehero;

import br.com.gubee.interview.util.BaseTestConfiguration;
import br.com.gubee.interview.util.TestFactory;
import br.com.gubee.interview.port.spi.hero.HeroRepository;
import br.com.gubee.interview.port.spi.powerstats.PowerStatsRepository;
import br.com.gubee.interview.web.adapter.in.comparehero.resources.CompareHeroesResponse;
import br.com.gubee.interview.web.adapter.in.hero.resources.HeroMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CompareHeroesControllerTest extends BaseTestConfiguration {

    @Autowired
    private PowerStatsRepository powerStatsRepository;
    @Autowired
    private HeroRepository heroRepository;
    private final String BASE_PATH = "/api/v1/compare";

    @Test
    void compareHeroesByName() throws Exception {
        //   given
        var firstStats = TestFactory.createPowerStats();
        var secondStats = TestFactory.createPowerStats();
        powerStatsRepository.create(firstStats);
        powerStatsRepository.create(secondStats);
        var firstHero = heroRepository.create(HeroMapper.toHero(TestFactory.createHeroRequest("Homem aranha", firstStats.getId())));
        var secondHero = heroRepository.create(HeroMapper.toHero(TestFactory.createHeroRequest("Homem formiga", secondStats.getId())));

        //when
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH)
                .param("firstHero", "Homem aranha")
                .param("secondHero", "Homem formiga"));

        //   then
        String content = actions.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        var difference = objectMapper.readValue(content, CompareHeroesResponse.class);
        assertEquals(difference.getFirst(), firstHero);
        assertEquals(difference.getSecond(), secondHero);
        Assertions.assertEquals(difference.getAgility(), firstStats.getAgility() - secondStats.getAgility());
        Assertions.assertEquals(difference.getDexterity(), firstStats.getDexterity() - secondStats.getDexterity());
        Assertions.assertEquals(difference.getStrength(), firstStats.getStrength() - secondStats.getStrength());
        Assertions.assertEquals(difference.getIntelligence(), firstStats.getIntelligence() - secondStats.getIntelligence());
        heroRepository.deleteById(firstHero);
        heroRepository.deleteById(secondHero);
        powerStatsRepository.deleteById(firstStats.getId());
        powerStatsRepository.deleteById(secondStats.getId());
    }

    @Test
    void compareHeroesNameNotFound() throws Exception {
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH)
                .param("firstHero", "Viúva negra")
                .param("secondHero", "Venom"));
        actions.andExpect(status().isNotFound());
    }

}