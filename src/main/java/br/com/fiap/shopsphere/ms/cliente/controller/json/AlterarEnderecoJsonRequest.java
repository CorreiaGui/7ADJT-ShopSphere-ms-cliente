package br.com.fiap.shopsphere.ms.cliente.controller.json;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AlterarEnderecoJsonRequest(
        @NotNull(message = "ID é obrigatório")
        @NotEmpty(message = "ID não pode ser vazio")
        UUID id,
        @NotNull(message = "Rua é obrigatório")
        @NotEmpty(message = "Rua não pode ser vazio")
        String rua,
        @NotNull(message = "Número é obrigatório")
        @NotEmpty(message = "Número não pode ser vazio")
        Integer numero,
        @NotNull(message = "CEP é obrigatório")
        @NotEmpty(message = "CEP não pode ser vazio")
        String cep,
        String complemento,
        @NotNull(message = "Bairro é obrigatório")
        @NotEmpty(message = "Bairro não pode ser vazio")
        String bairro,
        @NotNull(message = "Cidade é obrigatório")
        @NotEmpty(message = "Cidade não pode ser vazio")
        String cidade
) {}
