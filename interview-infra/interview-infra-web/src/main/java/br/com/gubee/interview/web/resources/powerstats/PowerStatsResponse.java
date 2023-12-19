package br.com.gubee.interview.web.resources.powerstats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class PowerStatsResponse {
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
}
