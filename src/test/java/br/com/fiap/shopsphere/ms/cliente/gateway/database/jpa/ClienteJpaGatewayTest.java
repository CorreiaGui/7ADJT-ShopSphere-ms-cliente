package br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa;

import br.com.fiap.shopsphere.ms.cliente.domain.Cliente;
import br.com.fiap.shopsphere.ms.cliente.domain.Endereco;
import br.com.fiap.shopsphere.ms.cliente.exception.RecursoExistenteException;
import br.com.fiap.shopsphere.ms.cliente.exception.RecursoNaoEncontradoException;
import br.com.fiap.shopsphere.ms.cliente.exception.UnprocessableEntityException;
import br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.entity.ClienteEntity;
import br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.entity.EnderecoEntity;
import br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.repository.ClienteRepository;
import br.com.fiap.shopsphere.ms.cliente.utils.ClienteUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteJpaGatewayTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteJpaGateway clienteJpaGateway;

    @Test
    void deveBuscarClientePorCpfQuandoExistir() {
        String cpf = "12345678901";
        ClienteEntity clienteEntity = criarMockClienteEntity();
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteEntity));

        Optional<Cliente> cliente = clienteJpaGateway.buscarClientePorCpf(cpf);

        assertTrue(cliente.isPresent());
        verify(clienteRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExistirAoBuscarPorCpf() {
        String cpf = "12345678901";
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> clienteJpaGateway.buscarClientePorCpf(cpf));
        verify(clienteRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void deveBuscarClientesComPaginacao() {
        int page = 0;
        int size = 2;
        ClienteEntity clienteEntity1 = criarMockClienteEntity();

        Page<ClienteEntity> clienteEntityPage = new PageImpl<>(List.of(clienteEntity1));
        when(clienteRepository.findAll(PageRequest.of(page, size))).thenReturn(clienteEntityPage);

        List<Cliente> clientes = clienteJpaGateway.buscarClientes(page, size);

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        verify(clienteRepository, times(1)).findAll(PageRequest.of(page, size));
    }

    @Test
    void deveCriarClienteQuandoCpfNaoExistir() {
        Cliente cliente = criarMockCliente();
        ClienteEntity clienteEntity = criarMockClienteEntity();
        when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(clienteEntity);

        Optional<Cliente> clienteCriado = clienteJpaGateway.criarCliente(cliente);

        assertTrue(clienteCriado.isPresent());
        assertEquals(cliente.getCpf(), clienteCriado.get().getCpf());
        verify(clienteRepository, times(1)).findByCpf(cliente.getCpf());
        verify(clienteRepository, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaExistirAoCriarCliente() {
        Cliente cliente = criarMockCliente();
        ClienteEntity clienteEntity = criarMockClienteEntity();
        when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(Optional.of(clienteEntity));

        assertThrows(RecursoExistenteException.class, () -> clienteJpaGateway.criarCliente(cliente));
        verify(clienteRepository, times(1)).findByCpf(cliente.getCpf());
        verify(clienteRepository, never()).save(any(ClienteEntity.class));
    }

    @Test
    void deveLancarExcecaoQuandoErroAoSalvarCliente() {
        Cliente cliente = criarMockCliente();
        when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(ClienteEntity.class))).thenThrow(new RuntimeException("Database error"));

        UnprocessableEntityException exception = assertThrows(UnprocessableEntityException.class,
                () -> clienteJpaGateway.criarCliente(cliente));

        assertEquals("Erro ao criar cliente: Database error", exception.getMessage());
        verify(clienteRepository, times(1)).findByCpf(cliente.getCpf());
        verify(clienteRepository, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    void deveAlterarClienteQuandoCpfExistir() {
        String cpf = "12345678901";
        Cliente cliente = criarMockCliente();
        ClienteEntity clienteEntity = criarMockClienteEntity();
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteEntity));
        when(clienteRepository.save(any())).thenReturn(clienteEntity);

        Optional<Cliente> clienteAtualizado = clienteJpaGateway.alterarClientePorCpf(cpf, cliente);

        assertTrue(clienteAtualizado.isPresent());
        verify(clienteRepository, times(1)).findByCpf(any());
        verify(clienteRepository, times(1)).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExistirAoAlterar() {
        String cpf = "12345678901";
        Cliente cliente = new Cliente();
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> clienteJpaGateway.alterarClientePorCpf(cpf, cliente));
        verify(clienteRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void deveExcluirClienteQuandoCpfExistir() {
        String cpf = "12345678901";
        when(clienteRepository.deleteByCpf(cpf)).thenReturn(1);

        assertDoesNotThrow(() -> clienteJpaGateway.excluirClientePorCpf(cpf));
        verify(clienteRepository, times(1)).deleteByCpf(cpf);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExistirAoExcluir() {
        String cpf = "12345678901";
        when(clienteRepository.deleteByCpf(cpf)).thenReturn(0);

        assertThrows(RecursoNaoEncontradoException.class, () -> clienteJpaGateway.excluirClientePorCpf(cpf));
        verify(clienteRepository, times(1)).deleteByCpf(cpf);
    }

    public Cliente criarMockCliente() {
        return Cliente.builder()
                .cpf("12345678901")
                .nome("John Doe")
                .dataNascimento(LocalDate.of(1985, 3, 25))
                .endereco(criarMockEndereco())
                .dataCriacao(LocalDateTime.now())
                .dataUltimaAlteracao(LocalDateTime.now())
            .build();
    }

    public Endereco criarMockEndereco() {
        return Endereco.builder()
                .id(UUID.fromString("a3059ff7-0aab-42b2-b062-0127c014ea14"))
                .rua("Street Name")
                .numero(123)
                .cep("12345-678")
                .complemento("Apartment 101")
                .bairro("Downtown")
                .cidade("City Name")
                .dataCriacao(LocalDateTime.now())
                .dataUltimaAlteracao(LocalDateTime.now())
            .build();
    }

    public ClienteEntity criarMockClienteEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf("12345678901");
        clienteEntity.setNome("John Doe");
        clienteEntity.setDataNascimento(LocalDate.of(1985, 3, 25));
        clienteEntity.setEndereco(criarMockEnderecoEntity());
        clienteEntity.setDataCriacao(LocalDateTime.now());
        clienteEntity.setDataUltimaAlteracao(LocalDateTime.now());
        return clienteEntity;
    }

    public EnderecoEntity criarMockEnderecoEntity() {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(UUID.fromString("a3059ff7-0aab-42b2-b062-0127c014ea14"));
        enderecoEntity.setRua("Street Name");
        enderecoEntity.setNumero(123);
        enderecoEntity.setCep("12345-678");
        enderecoEntity.setComplemento("Apartment 101");
        enderecoEntity.setBairro("Downtown");
        enderecoEntity.setCidade("City Name");
        enderecoEntity.setDataCriacao(LocalDateTime.now());
        enderecoEntity.setDataUltimaAlteracao(LocalDateTime.now());
        return enderecoEntity;
    }
}