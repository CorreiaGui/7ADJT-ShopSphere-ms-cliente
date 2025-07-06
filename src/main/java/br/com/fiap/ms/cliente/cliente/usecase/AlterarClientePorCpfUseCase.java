package br.com.fiap.ms.cliente.cliente.usecase;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlterarClientePorCpfUseCase {

    private final ClienteGateway clienteGateway;

    public Cliente alterarClientePorCpf(String cpf, Cliente cliente) {
        return clienteGateway.alterarClientePorCpf(cpf, cliente).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
}
