package com.example.cadastrodepalestras.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

@Entity  // Indica que esta classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("ORGANIZADOR")
@JsonPropertyOrder({"id", "nome", "email", "qtdeEventosAtivos"})
public class Organizador extends Usuario {

    @Column(unique = true) // Especifica que as colunas são obrigatória e únicas (sem repetições)
    @ToString.Include
    private Integer qtdeEventosAtivos;

}