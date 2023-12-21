package br.com.gubee.interview.model;

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
public class CompareHero {
    UUID first;
    UUID second;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
    
}
