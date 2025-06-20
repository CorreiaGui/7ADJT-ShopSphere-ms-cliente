package br.com.fiap.ms.cliente.cliente.gateway;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;

import java.util.Optional;

public interface  ClienteGateway {
    Optional<Cliente> buscarClientePorCpf(String cpf);
}
