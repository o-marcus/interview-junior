package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.util.Builder;
import br.com.gubee.interview.model.hero.dto.HeroRequest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class HeroServiceIT {

    @Autowired
    private HeroService heroService;

    HeroRequest request;

    @BeforeEach
    void init() {
        request = Builder.createHeroRequest();
    }

}