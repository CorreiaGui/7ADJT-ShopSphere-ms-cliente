package br.com.fiap.ms.cliente.cliente.usecase;

import br.com.fiap.ms.cliente.cliente.domain.Endereco;
import br.com.fiap.ms.cliente.cliente.gateway.EnderecoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarEnderecosUseCase {

    private final EnderecoGateway enderecoGateway;

    public List<Endereco> buscarEnderecos(int page, int size) {
        return enderecoGateway.buscarEnderecos(page, size);
    }
}
