package br.com.fiap.ms.cliente.cliente.usecase;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.exception.ResourceNotFoundException;
import br.com.fiap.ms.cliente.cliente.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarClientePorCpfUseCase {

    private final ClienteGateway clienteGateway;

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteGateway.buscarClientePorCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }
}
