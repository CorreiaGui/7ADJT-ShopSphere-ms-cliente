package br.com.fiap.ms.cliente.cliente.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

//id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
//nome VARCHAR(255) NOT NULL,
//cpf VARCHAR(11) NOT NULL,
//data_nascimento DATE NOT NULL,
//endereco UUID NOT NULL,
//data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
//data_ultima_alteracao TIMESTAMP,

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "cliente", schema = "ms_cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "endereco_id", nullable = false)
    private EnderecoEntity endereco;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @Column(name = "data_ultima_alteracao")
    private LocalDate dataUltimaAlteracao = LocalDate.now();
}
