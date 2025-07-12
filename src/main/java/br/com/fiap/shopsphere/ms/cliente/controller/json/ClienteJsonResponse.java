package br.com.fiap.shopsphere.ms.cliente.controller.json;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClienteJsonResponse(
    String cpf,
    String nome,
    LocalDate dataNascimento,
    LocalDateTime dataCriacao,
    LocalDateTime dataUltimaAlteracao,
    EnderecoJsonResponse endereco
) {}
