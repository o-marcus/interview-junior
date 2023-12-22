package br.com.gubee.interview.web.adapter.in.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.port.api.HeroService;
import br.com.gubee.interview.web.adapter.in.hero.resources.HeroMapper;
import br.com.gubee.interview.web.adapter.in.hero.resources.HeroRequest;
import br.com.gubee.interview.web.adapter.in.hero.resources.HeroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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
    public ResponseEntity<Void> create(@Validated @RequestBody HeroRequest heroRequest) {
        final UUID id = heroService.create(HeroMapper.toHero(heroRequest));
        return created(URI.create(format("/api/v1/heroes/%s", id))).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @Validated @RequestBody HeroRequest heroRequest) {
        heroService.updateHero(id, HeroMapper.toHero(heroRequest));
        return ok().body("updated hero with sucess");
   }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HeroResponse> getHeroByID(@PathVariable("id") UUID id) {
        Hero hero = heroService.findHero(id);
        return ok(HeroMapper.toHeroResponse(hero));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID id) {
        heroService.deleteHero(id);
        return ok("deleted sucessfully");
    }

    @GetMapping(path = "/search/{name}")
    public ResponseEntity<List<HeroResponse>> getHeroByNameLike(@PathVariable("name") String name) {
        List<Hero> heroes = heroService.findHero(name);
        var response = heroes
                .stream()
                .map(HeroMapper::toHeroResponse)
                .collect(Collectors.toList());
        return ok(response);
    }

}
