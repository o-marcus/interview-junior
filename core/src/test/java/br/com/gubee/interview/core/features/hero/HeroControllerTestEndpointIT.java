package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.util.TestFactory;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.hero.Hero;
import br.com.gubee.interview.model.hero.dto.HeroRequest;
import br.com.gubee.interview.model.hero.dto.HeroResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.UUID;
import java.util.function.Supplier;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("it")
@Testcontainers
@AutoConfigureMockMvc
class HeroControllerTestEndpointIT {

    @Container
    static final PostgreSQLContainer postgresContainer = new PostgreSQLContainer("postgres:12");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PowerStatsRepository powerStatsRepository;
    @Autowired
    private HeroRepository heroRepository;
    private  final String BASE_PATH = "/api/v1/heroes";
    UUID powerStatsId;
    UUID heroId;

    @AfterEach
    void afterEach() {
        if (powerStatsId != null) {
            heroRepository.deleteById(heroId);
            powerStatsRepository.deleteById(powerStatsId);
        }
    }

    @Test
    void create() throws Exception {
        powerStatsId = powerStatsRepository.create(TestFactory.createPowerStats());
        String body = objectMapper.writeValueAsString(TestFactory.createHeroRequest(powerStatsId));
        ResultActions actions = perform(body, () -> MockMvcRequestBuilders.post(BASE_PATH));
        actions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        heroId  = toUUID(actions);
        Hero hero = heroRepository.findById(heroId);
        Assertions.assertEquals(heroId, hero.getId());
    }

    @Test
    void createNotFoundPowerStatsId() throws Exception {
        String body = objectMapper.writeValueAsString(TestFactory.createHeroRequest());
        ResultActions actions = perform(body, () -> MockMvcRequestBuilders.post(BASE_PATH));
        actions.andExpect(status().isNotFound());
    }

    @Test
    void update() throws Exception {
        //  given
        powerStatsId = powerStatsRepository.create(TestFactory.createPowerStats());
        Hero flash = TestFactory.createHero(powerStatsId);
        heroId = heroRepository.create(TestFactory.createHero(powerStatsId));
        //  when
        flash.setRace(Race.DIVINE);
        ResultActions actions = perform(objectMapper.writeValueAsString(flash), () -> put(BASE_PATH+"/{id}", flash.getId()));
        //  then
        actions.andExpect(status().isOk());
        Assertions.assertEquals(Race.DIVINE, heroRepository.findById(heroId).getRace());
    }


    @Test
    void updateHeroIdNotFound() throws Exception {
        //  when
        ResultActions actions = perform(objectMapper.writeValueAsString(TestFactory.createHeroRequest()), () -> put(BASE_PATH+ "/{id}", UUID.randomUUID()));
        //  then
        actions.andExpect(status().isNotFound());
    }

    @Test
    void updatePowerStatsIdNotFound() throws Exception {
        Hero hero = heroRepository.findAll().get(0);
        HeroRequest request = TestFactory.createHeroRequest();
        request.setStats(UUID.randomUUID());
        request.setRace(Race.CYBORG);
        ResultActions actions = perform(objectMapper.writeValueAsString(request), () -> put(BASE_PATH+"/{id}", hero.getId()));
        actions.andExpect(status().isNotFound());
    }

    @Test
    void getHeroByID() throws Exception {
        Hero hero = heroRepository.findAll().get(0);
        ResultActions actions = perform(objectMapper.writeValueAsString(hero), () -> get(BASE_PATH+"/{id}", hero.getId()));
        String content = actions.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        HeroResponse response  = objectMapper.readValue(content, HeroResponse.class);
        Assertions.assertEquals(hero.getName(), response.getName());
        Assertions.assertEquals(hero.getRace(), response.getRace());
        Assertions.assertEquals(hero.getPowerStatsId(), response.getPowerStatsId());
    }

    @Test
    void deleteById() {
    }

    @Test
    void getHeroByNameLike() {
    }

    ResultActions perform(String body, Supplier<MockHttpServletRequestBuilder> method)
            throws Exception {
        return mockMvc.perform(method.get()
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
    }

    ResultActions perform(Supplier<MockHttpServletRequestBuilder> method)
            throws Exception {
        return mockMvc.perform(method.get());
    }


    UUID toUUID(ResultActions actions) {
        var uri = actions.andReturn().getResponse().getHeader("Location");
        if (uri == null) throw new RuntimeException();
        return UUID.fromString( uri.substring(uri.lastIndexOf("/") + 1));
    }


}