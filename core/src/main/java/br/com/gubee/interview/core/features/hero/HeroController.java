package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.dto.hero.HeroRequest;
import br.com.gubee.interview.model.dto.hero.HeroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroController {

    private final HeroService heroService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated
                                       @RequestBody HeroRequest heroDto) {
        final UUID id = heroService.create(heroDto);
        return created(URI.create(format("/api/v1/heroes/%s", id))).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @Validated @RequestBody HeroRequest heroDto) {
        heroService.update(id, heroDto);
        return ok().body("Updated hero with sucess");
   }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HeroResponse> getHeroByID(@PathVariable("id") UUID id) {
        HeroResponse hero = heroService.findById(id);
        return ok(hero);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") UUID id) {
        heroService.deleteById(id);
        return ok("deleted sucessfully");
    }

    @GetMapping(path = "/search/{name}")
    public ResponseEntity<List<HeroResponse>> getHeroByNameLike(@PathVariable("name") String name) {
        List<HeroResponse> heroes = heroService.findHeroByName(name);
        return ok(heroes);
    }
}
