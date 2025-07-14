package br.com.fiap.shopsphere.ms.cliente.gateway;

import br.com.fiap.shopsphere.ms.cliente.domain.Endereco;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnderecoGateway {
    Optional<Endereco> buscarEnderecoPorId(UUID id);
    List<Endereco> buscarEnderecos(int page, int size);
    Optional<Endereco> criarEndereco(Endereco endereco);
}
