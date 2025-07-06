package br.com.fiap.ms.cliente.cliente.usecase;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.domain.Endereco;
import br.com.fiap.ms.cliente.cliente.gateway.EnderecoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscarEnderecoPorIdUseCase {

    private final EnderecoGateway enderecoGateway;

    public Endereco buscarEnderecoPorId(UUID id) {
        return enderecoGateway.buscarEnderecoPorId(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }
}
