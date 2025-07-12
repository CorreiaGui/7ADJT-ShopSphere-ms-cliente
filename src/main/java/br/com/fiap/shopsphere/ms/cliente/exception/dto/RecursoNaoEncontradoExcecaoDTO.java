package br.com.fiap.shopsphere.ms.cliente.exception.dto;


public record RecursoNaoEncontradoExcecaoDTO(
    String errorMessage,
    int statusCode)
{}