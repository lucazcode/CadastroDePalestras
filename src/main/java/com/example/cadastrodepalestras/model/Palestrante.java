package com.example.cadastrodepalestras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity  // Indica que esta classe é uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados.
@Table(name = "palestrantes") // Especifica o nome da tabela no banco de dados que esta entidade irá mapear.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("PALESTRANTE")
public class Palestrante {

    @Id // Indica que o campo "id" é a chave primária da entidade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que o valor do campo "id" será gerado automaticamente
    @Column(nullable = false) // Especifica que a coluna "id" é obrigatória (não pode ser nula)
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true) // Especifica que as colunas são obrigatória e únicas (sem repetições)
    @ToString.Include
    private String nome;
    private String biografia;
    private String areaAtuacao;
    @ManyToMany(mappedBy = "palestrantes")
    @JsonIgnore
    private List<Palestra> palestras;
}