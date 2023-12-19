package br.com.gubee.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/*
 *
 *   Aqui estou seguindo o modelo de domínio anêmico,
 * pois nenhuma validação está sendo realizada ou regra de negócio.
 *
 *   Apesar de algumas bibliografias serem bem restritas com relação a anotações,
 * aqui usei um atalho ensinado no livro, que se permite a declaração de anotações./
 *
 * */
@Data
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
@Builder
public class PowerStats {
    private UUID id;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
    private Instant createdAt;
    private Instant updatedAt;
}
