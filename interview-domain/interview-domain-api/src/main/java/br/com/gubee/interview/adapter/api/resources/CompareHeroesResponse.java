package br.com.gubee.interview.adapter.api.resources;

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
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
}
