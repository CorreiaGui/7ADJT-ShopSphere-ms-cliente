package br.com.fiap.ms.cliente.cliente.exception.dto;


public record ResourceNotFoundDTO(
    String errorMessage,
    int statusCode)
{}