package br.com.fiap.ms.cliente.cliente.usecase;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.domain.Endereco;
import br.com.fiap.ms.cliente.cliente.gateway.EnderecoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarEnderecoUseCase {

    public final EnderecoGateway enderecoGateway;

    public Endereco criarEndereco(Endereco endereco) {
        return enderecoGateway.criarEndereco(endereco)
                .orElseThrow(() -> new RuntimeException("Erro ao criar endereco"));
    }
}
