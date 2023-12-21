package br.com.gubee.interview.web.adapter.in.comparehero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.port.api.CompareHeroesUseCase;
import br.com.gubee.interview.web.adapter.in.comparehero.resources.CompareHeroesResponse;
import br.com.gubee.interview.web.adapter.in.comparehero.resources.CompareMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/compare", produces = APPLICATION_JSON_VALUE)
public class CompareHeroesController {

    private final CompareHeroesUseCase compareUseCase;

    @GetMapping
    public ResponseEntity<CompareHeroesResponse> compareHeroesByName(@RequestParam String firstHero, @RequestParam String secondHero) {
        var response = compareUseCase.compare(firstHero, secondHero);
        return ResponseEntity.ok(CompareMapper.toCompareResponse(response));
    }

}
