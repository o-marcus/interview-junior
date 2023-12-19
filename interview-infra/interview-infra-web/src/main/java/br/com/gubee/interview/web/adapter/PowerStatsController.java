package br.com.gubee.interview.web.adapter;

import br.com.gubee.interview.port.api.PowerStatsService;
import br.com.gubee.interview.web.resources.powerstats.PowerStatsMapper;
import br.com.gubee.interview.web.resources.powerstats.PowerStatsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.net.BindException;
import java.net.URI;
import java.util.UUID;
import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/stats", produces = APPLICATION_JSON_VALUE)
public class PowerStatsController {

    private final PowerStatsService service;

    @ExceptionHandler(BindException.class)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated @RequestBody PowerStatsRequest stats) {
        final UUID id = service.create(PowerStatsMapper.toPowerStats(stats));
        return created(URI.create(format("/api/v1/heroes/%s", id))).build();
    }

}
