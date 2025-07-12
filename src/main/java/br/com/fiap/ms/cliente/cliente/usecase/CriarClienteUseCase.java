package br.com.fiap.ms.cliente.cliente.usecase;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.gateway.ClienteGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CriarClienteUseCase {

    public final ClienteGateway clienteGateway;

    public Optional<Cliente> criarCliente(Cliente cliente) {
        return clienteGateway.criarCliente(cliente);
    }
}
