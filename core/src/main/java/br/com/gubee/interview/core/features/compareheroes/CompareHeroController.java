package br.com.gubee.interview.core.features.compareheroes;

import br.com.gubee.interview.model.dto.compareheroes.CompareResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/compare", produces = APPLICATION_JSON_VALUE)
public class CompareHeroController {

    private final CompareHeroUseCase compareUseCase;

    @GetMapping
    public ResponseEntity<CompareResponse> getHeroByID(@RequestParam String firstHero, @RequestParam String secondHero) {
        CompareResponse compareResponse = compareUseCase.compare(firstHero, secondHero);
        return ResponseEntity.ok(compareResponse);
    }

}
