package br.com.fiap.ms.cliente.cliente.controller.json;

public record EnderecoJsonRequest(
        String rua,
        Integer numero,
        String cep,
        String complemento,
        String bairro,
        String cidade
) {}
