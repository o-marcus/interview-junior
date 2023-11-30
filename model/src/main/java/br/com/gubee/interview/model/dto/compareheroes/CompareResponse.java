package br.com.gubee.interview.model.dto.compareheroes;

import br.com.gubee.interview.model.dto.powerstats.PowerStatsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class CompareResponse {
    UUID first;
    UUID second;
    PowerStatsResponse difference;
}
