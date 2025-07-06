package br.com.fiap.ms.cliente.cliente.usecase;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.gateway.ClienteGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarClienteUseCase {

    public final ClienteGateway clienteGateway;

    public Cliente criarCliente(Cliente cliente) {
        return clienteGateway.criarCliente(cliente)
                .orElseThrow(() -> new RuntimeException("Erro ao criar cliente"));
    }
}
