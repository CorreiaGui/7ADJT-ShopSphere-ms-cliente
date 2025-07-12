package br.com.fiap.shopsphere.ms.cliente.exception.dto;


public record UnprocessableEntityExceptionDTO(
    String errorMessage,
    int statusCode)
{}