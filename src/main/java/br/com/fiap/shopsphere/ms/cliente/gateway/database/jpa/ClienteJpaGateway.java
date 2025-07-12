package br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa;

import br.com.fiap.shopsphere.ms.cliente.domain.Cliente;
import br.com.fiap.shopsphere.ms.cliente.exception.RecursoNaoEncontradoException;
import br.com.fiap.shopsphere.ms.cliente.exception.UnprocessableEntityException;
import br.com.fiap.shopsphere.ms.cliente.gateway.ClienteGateway;
import br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.entity.ClienteEntity;
import br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.repository.ClienteRepository;
import br.com.fiap.shopsphere.ms.cliente.utils.ClienteUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.fiap.shopsphere.ms.cliente.utils.ClienteUtils.convertToCliente;
import static br.com.fiap.shopsphere.ms.cliente.utils.ClienteUtils.uuidValidator;

@AllArgsConstructor
@Service
public class ClienteJpaGateway implements ClienteGateway {

    private final ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        return Optional.ofNullable(convertToCliente(clienteEntity));
    }

    @Override
    public List<Cliente> buscarClientes(int page, int size) {
        Page<ClienteEntity> clientesEntity = clienteRepository.findAll(PageRequest.of(page, size));
        return clientesEntity.map(ClienteUtils::convertToCliente).getContent();
    }

    @Override
    public Optional<Cliente> criarCliente(Cliente cliente) {
        ClienteEntity clienteEntity;
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new UnprocessableEntityException("Cliente com CPF já cadastrado");
        }
        try {
            clienteEntity = clienteRepository.save(ClienteUtils.convertToClienteEntity(cliente, null));
        } catch (Exception e) {
            throw new UnprocessableEntityException("Erro ao criar cliente: " + e.getMessage());
        }
        return Optional.ofNullable(ClienteUtils.convertToCliente(clienteEntity));
    }

    @Override
    public Optional<Cliente> alterarClientePorCpf(String cpf, Cliente cliente) {
        ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        uuidValidator(cliente.getEndereco().getId());
        ClienteEntity clienteAtualizado = clienteRepository.save(ClienteUtils.convertToClienteEntity(cliente, clienteEntity));
        return Optional.ofNullable(ClienteUtils.convertToCliente(clienteAtualizado));
    }

    @Override
    public void excluirClientePorCpf(String cpf) {
        int result = clienteRepository.deleteByCpf(cpf);
        if (result == 0) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado");
        }
    }
}
