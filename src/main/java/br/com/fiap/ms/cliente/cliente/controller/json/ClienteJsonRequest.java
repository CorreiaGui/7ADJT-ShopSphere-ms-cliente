package br.com.fiap.ms.cliente.cliente.controller.json;

import java.time.LocalDate;

public record ClienteJsonRequest(
    String cpf,
    String nome,
    LocalDate dataNascimento,
    EnderecoJsonRequest enderecoJsonRequest
) {}
