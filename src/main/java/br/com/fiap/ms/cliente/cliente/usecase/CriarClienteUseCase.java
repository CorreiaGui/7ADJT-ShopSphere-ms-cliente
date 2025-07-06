package br.com.fiap.ms.cliente.cliente.usecase;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.domain.Endereco;
import br.com.fiap.ms.cliente.cliente.gateway.ClienteGateway;
import br.com.fiap.ms.cliente.cliente.gateway.EnderecoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarClienteUseCase {

    public final ClienteGateway clienteGateway;
    public final EnderecoGateway enderecoGateway;

    public Cliente criarCliente(Cliente cliente) {
        Endereco endereco = enderecoGateway.buscarEnderecoPorId(cliente.getEndereco().getId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        cliente.setEndereco(endereco);
        return clienteGateway.criarCliente(cliente)
                .orElseThrow(() -> new RuntimeException("Erro ao criar cliente"));
    }
}
