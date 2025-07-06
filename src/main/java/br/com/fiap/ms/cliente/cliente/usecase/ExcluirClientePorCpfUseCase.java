package br.com.fiap.ms.cliente.cliente.usecase;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirClientePorCpfUseCase {
    private final ClienteGateway clienteGateway;

    public void excluirClientePorCpf(String cpf) {
        clienteGateway.excluirClientePorCpf(cpf);
    }
}
