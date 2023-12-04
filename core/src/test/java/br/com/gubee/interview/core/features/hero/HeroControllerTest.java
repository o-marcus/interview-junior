package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.NotFoundException;
import br.com.gubee.interview.core.features.util.TestFactory;
import br.com.gubee.interview.model.hero.Hero;
import br.com.gubee.interview.model.hero.dto.HeroRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.UUID;
import java.util.function.Supplier;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeroController.class)
class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private HeroService heroService;
    private final String BASE_PATH = "/api/v1/heroes";
    private HeroRequest request;
    @BeforeEach
    void init() {
        request = TestFactory.createHeroRequest();
    }

    @Test
    void createAHeroWithAllRequiredArguments() throws Exception {
        when(heroService.create(any())).thenReturn(UUID.randomUUID());
        final String body = objectMapper.writeValueAsString(request);
        final ResultActions actions = perform(body, () -> post(BASE_PATH));
        actions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        verify(heroService, times(1)).create(any());
    }

    @Test
    void createShouldNotFoundWhenIdPowerStatsIdDoesntExist() throws Exception {
        when(heroService.create(any())).thenThrow(new NotFoundException());
        final String body = objectMapper.writeValueAsString(request);
        ResultActions actions = perform(body, () -> post(BASE_PATH));
        actions.andExpect(status().isNotFound());
        verify(heroService, times(1)).create(any());
    }


    @Test
    void findByIdWithRequiredArguments() throws Exception {
        when(heroService.findById(isA(UUID.class))).thenReturn(TestFactory.createHero());
        ResultActions actions = perform(() -> MockMvcRequestBuilders.get(BASE_PATH + "/{id}", UUID.randomUUID()));
        actions.andExpect(status().isOk());
        verify(heroService, times(1)).findById(any(UUID.class));
    }

    @Test
    void findByIdShouldReturnNotFoundWhenHeroIdNotExists() throws Exception {
        when(heroService.findById(any(UUID.class))).thenThrow(new NotFoundException());
        ResultActions actions = perform(() -> MockMvcRequestBuilders.get(BASE_PATH + "/{id}", UUID.randomUUID()));
        actions.andExpect(status().isNotFound());
        verify(heroService, times(1)).findById(any(UUID.class));
    }

    @Test
    void updateHeroWithRequiredArguments() throws Exception {
        var body = objectMapper.writeValueAsString(request);
        ResultActions actions = perform(body, () -> MockMvcRequestBuilders.put(BASE_PATH + "/{id}", UUID.randomUUID()));
        actions.andExpect(status().isOk());
        verify(heroService, times(1)).update(any(UUID.class), any(Hero.class));
    }

    @Test
    void updateHeroShouldReturnNotFoundWhenHeroIdNotExists() throws Exception {
        doThrow(new NotFoundException()).when(heroService).update(any(UUID.class), any(Hero.class));
        var body = objectMapper.writeValueAsString(request);
        ResultActions actions = perform(body, () -> MockMvcRequestBuilders.put(BASE_PATH + "/{id}", UUID.randomUUID()));
        actions.andExpect(status().isNotFound());
        verify(heroService, times(1)).update(any(UUID.class), any(Hero.class));
    }

    @Test
    void deleteHeroWithRequiredArguments() throws Exception {
        ResultActions actions = perform(() -> MockMvcRequestBuilders.delete(BASE_PATH + "/{id}", UUID.randomUUID()));
        actions.andExpect(status().isOk());
        verify(heroService, times(1)).deleteById(any(UUID.class));
    }

    @Test void deleteHeroShouldReturnNotFoundWhenHeroIdNotExists() throws Exception {
        doThrow(new NotFoundException()).when(heroService).deleteById(any(UUID.class));
        ResultActions actions = perform(() -> MockMvcRequestBuilders.delete(BASE_PATH + "/{id}", UUID.randomUUID()));
        actions.andExpect(status().isNotFound());
        verify(heroService, times(1)).deleteById(any(UUID.class));
    }

    ResultActions perform(String body, Supplier<MockHttpServletRequestBuilder> method) throws Exception {
        return mockMvc.perform(method.get()
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
    }

    ResultActions perform(Supplier<MockHttpServletRequestBuilder> method) throws Exception {
        return mockMvc.perform(method.get());
    }

}