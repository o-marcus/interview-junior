package br.com.gubee.interview.model.stub;

import br.com.gubee.interview.model.Hero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class HeroRepositoryStub {

    private List<Hero> heroes = new ArrayList<>();

    public UUID create(Hero hero) {
        UUID id = UUID.randomUUID();
        hero.setPowerStatsId(id);
        heroes.add(hero);
        return id;
    }

    public Hero findById(UUID id) {
        return heroes.stream()
                .filter(hero -> hero.getId().equals(id))
                .findFirst()
                .get();
    }

}
