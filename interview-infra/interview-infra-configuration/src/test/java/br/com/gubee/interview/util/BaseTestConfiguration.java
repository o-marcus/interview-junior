package br.com.gubee.interview.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = "br.com.gubee.interview")
@ActiveProfiles("it")
@Testcontainers
@AutoConfigureMockMvc
public abstract class BaseTestConfiguration {

    @Container
    protected static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:12")
            .withDatabaseName("interview-test-service")
            .withUsername("gubee")
            .withPassword("gubee")
            .withReuse(false);

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @DynamicPropertySource
    private static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
        dymDynamicPropertyRegistry.add( "jdbc.url", postgresContainer::getJdbcUrl);
        dymDynamicPropertyRegistry.add( "jdbc.username", postgresContainer::getUsername);
        dymDynamicPropertyRegistry.add( "jdbc.password", postgresContainer::getPassword);
    }

}
