package br.com.gubee.interview.model.compareheroes;

import br.com.gubee.interview.model.powerstats.dto.PowerStatsResponse;
import lombok.*;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class CompareHeroesResponse {
    UUID first;
    UUID second;
    PowerStatsResponse difference;
}
