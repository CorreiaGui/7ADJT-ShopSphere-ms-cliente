package br.com.fiap.shopsphere.ms.cliente.usecase;

import br.com.fiap.shopsphere.ms.cliente.domain.Cliente;
import br.com.fiap.shopsphere.ms.cliente.exception.RecursoNaoEncontradoException;
import br.com.fiap.shopsphere.ms.cliente.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarClientePorCpfUseCase {

    private final ClienteGateway clienteGateway;

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteGateway.buscarClientePorCpf(cpf).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
    }
}
