package br.com.gubee.interview.web.adapter.out.hero.resources;

import br.com.gubee.interview.enums.Race;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class HeroRequest {
    @NotBlank(message = "message.name.mandatory")
    @Length(min = 1, max = 255, message = "message.name.length")
    private String name;
    @NotNull(message = "message.race.mandatory")
    private Race race;
    @NotNull(message = "message.stats.mandatory")
    private UUID stats;
}
