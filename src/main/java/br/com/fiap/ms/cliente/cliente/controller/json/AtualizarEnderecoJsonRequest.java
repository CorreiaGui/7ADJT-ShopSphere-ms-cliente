package br.com.fiap.ms.cliente.cliente.controller.json;

import java.util.UUID;

public record AtualizarEnderecoJsonRequest(
        UUID id,
        String rua,
        Integer numero,
        String cep,
        String complemento,
        String bairro,
        String cidade
) {}
