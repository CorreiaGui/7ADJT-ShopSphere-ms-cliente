package br.com.fiap.ms.cliente.cliente.exception.dto;


public record UnprocessableEntityExceptionDTO(
    String errorMessage,
    int statusCode)
{}