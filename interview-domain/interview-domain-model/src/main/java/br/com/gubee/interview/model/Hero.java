package br.com.gubee.interview.model;

import br.com.gubee.interview.enums.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;
import static lombok.AccessLevel.PRIVATE;

//
//  dizem que adicionar anotações é contra as regras, então usei um atalho que o próprio livro ensinou declarando as anotações direto nos elementos.

/*
*
*   Aqui estou seguindo o modelo de domínio anêmico,
* pois nenhuma validação está sendo realizada.
*
*   Apesar de algumas bibliografias serem bem restritas com relação a anotações,
* aqui usei um atalho ensinado no livro, que se baseia em declrarar ano./
*
* */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class Hero {
    private UUID id;
    private String name;
    private Race race;
    private UUID powerStatsId;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean enabled;
}
