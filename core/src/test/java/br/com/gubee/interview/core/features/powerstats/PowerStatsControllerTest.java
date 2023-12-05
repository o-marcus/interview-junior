package br.com.gubee.interview.core.features.powerstats;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.UUID;
import static br.com.gubee.interview.core.features.util.TestFactory.createPowerStatsRequest;
import static br.com.gubee.interview.core.features.util.Util.toUUID;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("it")
@Testcontainers
@AutoConfigureMockMvc
class PowerStatsControllerTest {

    @Container
    static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:12")
            .withReuse(false);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PowerStatsRepository powerStatsRepository;
    private final String BASE_PATH = "/api/v1/stats";

    @Test
    void create() throws Exception {
        var request = createPowerStatsRequest();
        var body = objectMapper.writeValueAsString(request);

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        actions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        UUID id = toUUID(actions);
        Assertions.assertDoesNotThrow(() -> {
            powerStatsRepository.findById(id);
        });
        powerStatsRepository.deleteById(id);
    }

}