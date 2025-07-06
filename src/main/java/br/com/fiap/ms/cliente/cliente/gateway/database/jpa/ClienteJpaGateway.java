package br.com.fiap.ms.cliente.cliente.gateway.database.jpa;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.domain.Endereco;
import br.com.fiap.ms.cliente.cliente.gateway.ClienteGateway;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.entity.ClienteEntity;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.repository.ClienteRepository;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.repository.EnderecoRepository;
import br.com.fiap.ms.cliente.cliente.utils.ClienteUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static br.com.fiap.ms.cliente.cliente.utils.ClienteUtils.convertToCliente;
import static br.com.fiap.ms.cliente.cliente.utils.ClienteUtils.convertToClienteEntity;

@AllArgsConstructor
@Component
public class ClienteJpaGateway implements ClienteGateway {

    private final ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Optional<Cliente> cliente = Optional.ofNullable(convertToCliente(clienteEntity));
        return cliente;
    }

    @Override
    public List<Cliente> buscarClientes(int page, int size) {
        Page<ClienteEntity> clientesEntity = clienteRepository.findAll(PageRequest.of(page, size));
        List<Cliente> list = clientesEntity.map(ClienteUtils::convertToCliente).getContent();
        return list;
    }

    @Override
    public Optional<Cliente> criarCliente(Cliente cliente) {
        var clienteEntity = clienteRepository.save(ClienteUtils.convertToClienteEntity(cliente));
        return Optional.ofNullable(ClienteUtils.convertToCliente(clienteEntity));
    }

    @Override
    public Optional<Cliente> alterarClientePorCpf(String cpf, Cliente cliente) {
        ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Optional<Cliente> cliente2 = Optional.ofNullable(convertToCliente(clienteEntity));
        clienteEntity.setCpf(cpf);

        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setDataNascimento(cliente.getDataNascimento());
        clienteEntity.getEndereco().setId(cliente.getEndereco().getId());
        clienteEntity.getEndereco().setRua(cliente.getEndereco().getRua());
        clienteEntity.getEndereco().setNumero(cliente.getEndereco().getNumero());
        clienteEntity.getEndereco().setCep(cliente.getEndereco().getCep());
        clienteEntity.getEndereco().setComplemento(cliente.getEndereco().getComplemento());
        clienteEntity.getEndereco().setBairro(cliente.getEndereco().getBairro());
        clienteEntity.getEndereco().setCidade(cliente.getEndereco().getCidade());
        clienteEntity.setDataUltimaAlteracao(LocalDateTime.now());

        ClienteEntity clienteAtualizado = clienteRepository.save(clienteEntity);
        return Optional.ofNullable(ClienteUtils.convertToCliente(clienteAtualizado));
    }

    @Override
    public void excluirClientePorCpf(String cpf) {
        clienteRepository.deleteById(cpf);
    }
}
