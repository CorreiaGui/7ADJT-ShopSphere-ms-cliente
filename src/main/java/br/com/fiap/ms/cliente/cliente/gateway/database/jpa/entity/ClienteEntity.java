package br.com.fiap.ms.cliente.cliente.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "cliente", schema = "ms_cliente")
public class ClienteEntity {

    @Id
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "endereco_id", nullable = false)
    private EnderecoEntity endereco;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao = LocalDateTime.now();
}
