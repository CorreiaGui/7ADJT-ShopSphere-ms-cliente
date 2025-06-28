package br.com.fiap.ms.cliente.cliente.controller.json;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
