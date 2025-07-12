package br.com.fiap.shopsphere.ms.cliente.gateway;

import br.com.fiap.shopsphere.ms.cliente.domain.Cliente;

import java.util.List;
import java.util.Optional;

public interface  ClienteGateway {
    Optional<Cliente> buscarClientePorCpf(String cpf);
    List<Cliente> buscarClientes(int page, int size);
    Optional<Cliente> criarCliente(Cliente cliente);
    Optional<Cliente> alterarClientePorCpf(String cpf, Cliente cliente);
    void excluirClientePorCpf(String cpf);
}
