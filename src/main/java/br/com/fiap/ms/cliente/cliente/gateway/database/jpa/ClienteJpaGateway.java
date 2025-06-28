package br.com.fiap.ms.cliente.cliente.gateway.database.jpa;

import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.gateway.ClienteGateway;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.entity.ClienteEntity;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.repository.ClienteRepository;
import br.com.fiap.ms.cliente.cliente.utils.ClienteUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import static br.com.fiap.ms.cliente.cliente.utils.ClienteUtils.convertToCliente;

@AllArgsConstructor
@Component
public class ClienteJpaGateway implements ClienteGateway {

    private final ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return Optional.ofNullable(convertToCliente(clienteEntity));
    }

    @Override
    public List<Cliente> buscarClientes(int page, int size) {
        Page<ClienteEntity> clientesEntity = clienteRepository.findAll(PageRequest.of(page, size));
        return clientesEntity.map(ClienteUtils::convertToCliente).getContent();
    }
}
