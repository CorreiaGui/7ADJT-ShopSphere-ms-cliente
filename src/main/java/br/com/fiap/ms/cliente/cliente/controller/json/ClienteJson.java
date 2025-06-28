package br.com.fiap.ms.cliente.cliente.controller.json;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
/*
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString*/
public record ClienteJson(
    String cpf,
    String nome,
    LocalDate dataNascimento,
    String rua,
    Integer numero,
    String complemento,
    String bairro,
    String cidade,
    LocalDateTime dataCriacao,
    LocalDateTime dataUltimaAlteracao
) {}
