package br.com.fiap.shopsphere.ms.cliente.usecase;

import br.com.fiap.shopsphere.ms.cliente.gateway.ClienteGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ExcluirClientePorCpfUseCase {

    private final ClienteGateway clienteGateway;

    public void excluirClientePorCpf(String cpf) {
        clienteGateway.excluirClientePorCpf(cpf);
    }
}