package br.com.fiap.ms.cliente.cliente.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Cliente {

    private String cpf;

    private String nome;

    private LocalDate dataNascimento;

    private Endereco endereco;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataUltimaAlteracao;
}
