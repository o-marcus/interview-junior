package br.com.gubee.interview.port.spi.entities;

import lombok.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeroPowerStats {
    UUID id;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
}
