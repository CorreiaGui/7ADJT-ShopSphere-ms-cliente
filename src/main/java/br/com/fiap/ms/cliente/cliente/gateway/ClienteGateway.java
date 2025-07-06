package br.com.fiap.ms.cliente.cliente.gateway;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.domain.Endereco;

import java.util.List;
import java.util.Optional;

public interface  ClienteGateway {
    Optional<Cliente> buscarClientePorCpf(String cpf);
    List<Cliente> buscarClientes(int page, int size);
    Optional<Cliente> criarCliente(Cliente cliente);
}
