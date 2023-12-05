package br.com.gubee.interview.core.features.compareheroes;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.model.compareheroes.CompareHeroesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static br.com.gubee.interview.core.features.hero.HeroMapper.toHero;
import static br.com.gubee.interview.core.features.util.TestFactory.createHeroRequest;
import static br.com.gubee.interview.core.features.util.TestFactory.createPowerStats;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("it")
@Testcontainers
@AutoConfigureMockMvc
class CompareHeroesControllerTest {

    @Container
    static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:12")
            .withReuse(false);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PowerStatsRepository powerStatsRepository;
    @Autowired
    private HeroRepository heroRepository;
    private final String BASE_PATH = "/api/v1/compare";

    @Test
    void compareHeroesByName() throws Exception {
        //   given
        var firstStats = createPowerStats();
        var secondStats = createPowerStats();
        powerStatsRepository.create(firstStats);
        powerStatsRepository.create(secondStats);
        var firstHero = heroRepository.create(toHero(createHeroRequest("Homem aranha", firstStats.getId())));
        var secondHero = heroRepository.create(toHero(createHeroRequest("Homem formiga", secondStats.getId())));

        //when
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH)
                .param("firstHero", "Homem aranha")
                .param("secondHero", "Homem formiga"));

        //   then
        String content = actions.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        var response = objectMapper.readValue(content, CompareHeroesResponse.class);
        var difference = response.getDifference();
        assertEquals(response.getFirst(), firstHero);
        assertEquals(response.getSecond(), secondHero);
        assertEquals(difference.getAgility(), firstStats.getAgility() - secondStats.getAgility());
        assertEquals(difference.getDexterity(), firstStats.getDexterity() - secondStats.getDexterity());
        assertEquals(difference.getStrength(), firstStats.getStrength() - secondStats.getStrength());
        assertEquals(difference.getIntelligence(), firstStats.getIntelligence() - secondStats.getIntelligence());
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