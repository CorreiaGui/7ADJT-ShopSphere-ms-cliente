package br.com.fiap.shopsphere.ms.cliente.controller.json;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AlterarClienteJsonRequest(
    @NotNull(message = "CPF é obrigatório")
    @NotEmpty(message = "CPF não pode ser vazio")
    String cpf,
    @NotNull(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome não pode ser vazio")
    String nome,
    @NotNull(message = "Data de nascimento é obrigatório")
    @NotEmpty(message = "Data de nascimento não pode ser vazio")
    LocalDate dataNascimento,
    AlterarEnderecoJsonRequest enderecoJsonRequest
) {}
