package br.com.fiap.ms.cliente.cliente.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    private UUID id;

    private String nome;

    private LocalDate dataNascimento;

    private Endereco endereco;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataUltimaAlteracao;
}
