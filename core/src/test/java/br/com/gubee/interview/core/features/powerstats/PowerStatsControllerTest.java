package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.features.hero.BaseTestConfiguration;
import br.com.gubee.interview.model.powerstats.PowerStatsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.UUID;
import static br.com.gubee.interview.core.features.util.TestFactory.createPowerStatsRequest;
import static br.com.gubee.interview.core.features.util.Util.toUUID;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PowerStatsControllerTest extends BaseTestConfiguration {

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