package br.com.fiap.ms.cliente.cliente.gateway.database.jpa;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.domain.Endereco;
import br.com.fiap.ms.cliente.cliente.gateway.ClienteGateway;
import br.com.fiap.ms.cliente.cliente.gateway.EnderecoGateway;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.entity.ClienteEntity;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.entity.EnderecoEntity;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.repository.ClienteRepository;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.repository.EnderecoRepository;
import br.com.fiap.ms.cliente.cliente.utils.ClienteUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.ms.cliente.cliente.utils.ClienteUtils.convertToCliente;
import static br.com.fiap.ms.cliente.cliente.utils.ClienteUtils.uuidValidator;

@AllArgsConstructor
@Component
public class EnderecoJpaGateway implements EnderecoGateway {

    private final EnderecoRepository enderecoRepository;

    @Override
    public Optional<Endereco> buscarEnderecoPorId(UUID id) {
        uuidValidator(id);
        var enderecoEntity = enderecoRepository.findById(id);
        return Optional.ofNullable(ClienteUtils.convertToEndereco(enderecoEntity));
    }

    @Override
    public List<Endereco> buscarEnderecos(int page, int size) {
        Page<EnderecoEntity> enderecosEntity = enderecoRepository.findAll(PageRequest.of(page, size));
        return enderecosEntity.map(ClienteUtils::convertToEndereco).getContent();
    }

    @Override
    public Optional<Endereco> criarEndereco(Endereco endereco) {
        var enderecoEntity = enderecoRepository.save(ClienteUtils.convertToEnderecoEntity(endereco));
        return Optional.ofNullable(ClienteUtils.convertToEndereco(Optional.ofNullable(enderecoEntity)));
    }
}
