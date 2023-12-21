package br.com.gubee.interview.powerstats;

import br.com.gubee.interview.BaseTestConfiguration;
import br.com.gubee.interview.model.powerstats.PowerStatsRepository;
import br.com.gubee.interview.TestFactory;
import br.com.gubee.interview.Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PowerStatsControllerTest extends BaseTestConfiguration {

    @Autowired
    private PowerStatsRepository powerStatsRepository;
    private final String BASE_PATH = "/api/v1/stats";

    @Test
    void create() throws Exception {
        var request = TestFactory.createPowerStatsRequest();
        var body = objectMapper.writeValueAsString(request);

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        actions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        UUID id = Util.toUUID(actions);
        assertDoesNotThrow(() -> {
            powerStatsRepository.findById(id);
        });
        powerStatsRepository.deleteById(id);
    }

}
