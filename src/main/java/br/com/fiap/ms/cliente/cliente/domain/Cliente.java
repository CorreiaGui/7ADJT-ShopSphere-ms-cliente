package br.com.fiap.ms.cliente.cliente.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private UUID id;

    private String nome;

    private LocalDate dataNascimento;

    private Endereco endereco;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataUltimaAlteracao;

}
