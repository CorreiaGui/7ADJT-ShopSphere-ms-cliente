package br.com.fiap.ms.cliente.cliente.exception.dto;


public record ResourceExistingExceptionDTO(
    String errorMessage,
    int statusCode)
{}