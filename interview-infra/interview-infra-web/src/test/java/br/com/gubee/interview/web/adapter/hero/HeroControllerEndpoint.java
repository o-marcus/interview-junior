package br.com.gubee.interview.web.adapter.hero;

import br.com.gubee.interview.model.hero.Hero;
import br.com.gubee.interview.model.hero.HeroRepository;
import br.com.gubee.interview.model.powerstats.PowerStatsRepository;
import br.com.gubee.interview.util.BaseTestConfiguration;
import br.com.gubee.interview.util.TestFactory;
import br.com.gubee.interview.util.Util;
import br.com.gubee.interview.web.resources.hero.HeroRequest;
import br.com.gubee.interview.web.resources.hero.HeroResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HeroControllerEndpoint extends BaseTestConfiguration {

    @Autowired
    private PowerStatsRepository powerStatsRepository;
    @Autowired
    private HeroRepository heroRepository;
    private final String BASE_PATH = "/api/v1/heroes";
    UUID powerStatsId;
    UUID heroId;

    @AfterEach
    void afterEach() {
        if (powerStatsId != null) {
            if (heroId != null) {
                heroRepository.deleteById(heroId);
            }
            powerStatsRepository.deleteById(powerStatsId);
        }
    }

    @Test
    void create() throws Exception {
        powerStatsId = powerStatsRepository.create(TestFactory.createPowerStats());
        String body = objectMapper.writeValueAsString(TestFactory.createHeroRequest(powerStatsId));
        ResultActions actions = perform(body, () -> post(BASE_PATH));
        actions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        heroId = Util.toUUID(actions);
        Hero hero = heroRepository.findById(heroId);
        Assertions.assertEquals(heroId, hero.getId());
    }

    @Test
    void createNotFoundPowerStatsId() throws Exception {
        String body = objectMapper.writeValueAsString(TestFactory.createHeroRequest());
        ResultActions actions = perform(body, () -> post(BASE_PATH));
        actions.andExpect(status().isNotFound());
    }

    @Test
    void update() throws Exception {
        //  given
        powerStatsId = powerStatsRepository.create(TestFactory.createPowerStats());
        HeroRequest flash = TestFactory.createHeroRequest(powerStatsId);
        heroId = heroRepository.create(HeroMapper.toHero(flash));
        //  when
        ResultActions actions = perform(
                objectMapper.writeValueAsString(TestFactory.createHeroRequest("Superman", powerStatsId))
                , () -> put(BASE_PATH + "/{id}", heroId)
        );
        //  then
        Hero hero = heroRepository.findById(heroId);
        actions.andExpect(status().isOk());
        Assertions.assertEquals("Superman", hero.getName());
    }


    @Test
    void updateHeroIdNotFound() throws Exception {
        ResultActions actions = perform(objectMapper.writeValueAsString(TestFactory.createHeroRequest()), () -> put(BASE_PATH + "/{id}", UUID.randomUUID()));
        actions.andExpect(status().isNotFound());
    }

    @Test
    void updatePowerStatsIdNotFound() throws Exception {
        //  given
        powerStatsId = powerStatsRepository.create(TestFactory.createPowerStats());
        var request = TestFactory.createHeroRequest(powerStatsId);
        heroId = heroRepository.create(HeroMapper.toHero(request));
        //  when
        request.setStats(UUID.randomUUID());
        //  then
        ResultActions actions = perform(objectMapper.writeValueAsString(request), () -> put(BASE_PATH + "/{id}", heroId));
        actions.andExpect(status().isNotFound());
    }

    @Test
    void getHeroByID() throws Exception {
        powerStatsId = powerStatsRepository.create(TestFactory.createPowerStats());
        var request = TestFactory.createHeroRequest(powerStatsId);
        heroId = heroRepository.create(HeroMapper.toHero(request));
        ResultActions actions = perform(() -> get(BASE_PATH + "/{id}", heroId));
        String content = actions.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        HeroResponse response = objectMapper.readValue(content, HeroResponse.class);
        Assertions.assertEquals(request.getName(), response.getName());
        Assertions.assertEquals(request.getRace(), response.getRace());
        Assertions.assertEquals(request.getStats(), response.getPowerStatsId());
    }

    @Test
    void deleteById() throws Exception {
        powerStatsId = powerStatsRepository.create(TestFactory.createPowerStats());
        var request = TestFactory.createHeroRequest(powerStatsId);
        heroId = heroRepository.create(HeroMapper.toHero(request));
        ResultActions actions = perform(() -> delete(BASE_PATH + "/{id}", heroId));
        actions.andExpect(status().isOk());
        Assertions.assertThrows(NotFoundException.class, () -> {
           heroRepository.findById(heroId);
        });
        heroId = null;
    }

    @Test
    void deleteHeroIdNotFound() throws Exception {
        ResultActions actions = perform(() -> delete(BASE_PATH + "/{id}", UUID.randomUUID()));
        actions.andExpect(status().isNotFound());
    }

    @Test
    void getHeroByNameLike() throws Exception {
        //  given
        powerStatsId = powerStatsRepository.create(TestFactory.createPowerStats());
        List<Hero> heroes = Stream.of("vanderson", "evandro", "ivan")
                .map(name -> TestFactory.createHeroRequest(name, powerStatsId))
                .map(HeroMapper::toHero)
                .collect(Collectors.toList());
        List<UUID> ids = heroes.stream().map(hero -> heroRepository.create(hero)).collect(Collectors.toList());
        //  when
        ResultActions actions = perform(() -> get(BASE_PATH+"/search/van"));
        //  then
        actions.andExpect(status().isOk());
        String contentAsString = actions.andReturn().getResponse().getContentAsString();
        HeroResponse[] heroResponses = objectMapper.readValue(contentAsString, HeroResponse[].class);
        Assertions.assertEquals(3, heroResponses.length);
        //  Com assertJ -> assertThat(heroResponses).hasSize(3);
        ids.forEach(id -> heroRepository.deleteById(id));
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

}