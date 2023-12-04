package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.NotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.core.features.util.TestFactory;
import br.com.gubee.interview.model.hero.dto.HeroRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("it")
public class HeroServiceIT {


//    @Test
//    void createHeroWithRequiredParamenters() {
//        //  given
//        UUID powerId = statsService.create(TestFactory.createPowerStatsRequest());
//        HeroRequest request = TestFactory.createHeroRequest(powerId);
//
//        //  when
//        UUID heroId = heroService.create(request);
//
//        //  then
//        var response = heroService.findById(heroId);
//        Assertions.assertEquals(response.getName(), request.getName());
//        Assertions.assertEquals(response.getRace(), request.getRace());
//        Assertions.assertEquals(response.getPowerStatsId(), request.getStats());
//    }
//
//    @Test
//    void createHeroThrowExcpetionWhenPowerStatsIdNotExists() {
//        Assertions.assertThrows(NotFoundException.class, () -> {
//            heroService.create(TestFactory.createHeroRequest());
//        });
//    }
//
//    @Test
//    void findByNameWithRequiredParameters() {
//
//        //  given
//        UUID powerId = statsService.create(TestFactory.createPowerStatsRequest());
//        List<HeroRequest> flashUniverse = IntStream.range(0, 5)
//                .mapToObj(value ->  TestFactory.createHeroRequest(powerId))
//                .collect(Collectors.toList());
//        //  given
//        flashUniverse.forEach(flash -> );
//        HeroRequest request = TestFactory.createHeroRequest(powerId);
//
//    }



}