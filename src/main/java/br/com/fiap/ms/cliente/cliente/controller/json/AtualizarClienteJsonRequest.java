package br.com.fiap.ms.cliente.cliente.controller.json;

import java.time.LocalDate;

public record AtualizarClienteJsonRequest(
    String cpf,
    String nome,
    LocalDate dataNascimento,
    AtualizarEnderecoJsonRequest enderecoJsonRequest
) {}
