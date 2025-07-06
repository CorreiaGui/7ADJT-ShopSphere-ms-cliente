package br.com.fiap.ms.cliente.cliente.controller.json;

import java.time.LocalDate;
import java.util.UUID;

public record ClienteJsonRequest(
    String cpf,
    String nome,
    LocalDate dataNascimento,
    UUID idEndereco
) {}
