package br.com.fiap.shopsphere.ms.cliente.exception.dto;


public record RecursoExistenteExcecaoDTO(
    String errorMessage,
    int statusCode)
{}