package br.com.fiap.ms.cliente.cliente.usecase;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarClientesUseCase {

    private final ClienteGateway clienteGateway;

    public List<Cliente> buscarClientes(int page, int size) {
        return clienteGateway.buscarClientes(page, size);
    }
}
