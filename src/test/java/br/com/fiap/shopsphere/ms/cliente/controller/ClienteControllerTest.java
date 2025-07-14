package br.com.fiap.shopsphere.ms.cliente.controller;

import br.com.fiap.shopsphere.ms.cliente.controller.json.*;
import br.com.fiap.shopsphere.ms.cliente.domain.Cliente;
import br.com.fiap.shopsphere.ms.cliente.domain.Endereco;
import br.com.fiap.shopsphere.ms.cliente.usecase.*;
import br.com.fiap.shopsphere.ms.cliente.utils.ClienteUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private BuscarClientePorCpfUseCase buscarClientePorCpfUseCase;

    @Mock
    private BuscarClientesUseCase buscarClientesUseCase;

    @Mock
    private CriarClienteUseCase criarClienteUseCase;

    @Mock
    private AlterarClientePorCpfUseCase atualizarClientePorCpfUseCase;

    @Mock
    private ExcluirClientePorCpfUseCase excluirClientePorCpfUseCase;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void deveBuscarClientePorCpf() {
        String cpf = "12345678901";
        Cliente cliente = criarMockCliente();
        ClienteJsonResponse response = criarMockClienteJsonResponse();

        when(buscarClientePorCpfUseCase.buscarClientePorCpf(anyString())).thenReturn(cliente);

        ResponseEntity<ClienteJsonResponse> result = clienteController.buscarClientePorCpf(cpf);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
    }

    @Test
    void deveBuscarClientesComPaginacao() {
        List<Cliente> clientes = List.of(criarMockCliente());
        List<ClienteJsonResponse> responses = List.of(criarMockClienteJsonResponse());

        when(buscarClientesUseCase.buscarClientes(0, 10)).thenReturn(clientes);

        ResponseEntity<List<ClienteJsonResponse>> result = clienteController.buscarClientes(0, 10);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(responses, result.getBody());
    }

    @Test
    void deveCriarCliente() {
        ClienteJsonRequest request = criarMockClienteJsonRequest();
        Cliente cliente = criarMockCliente();
        ClienteJsonResponse response = criarMockClienteJsonResponse();

        when(criarClienteUseCase.criarCliente(any())).thenReturn(Optional.of(cliente));

        ResponseEntity<ClienteJsonResponse> result = clienteController.criarCliente(request);

        assertEquals(201, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
    }

    @Test
    void deveAlterarCliente() {
        String cpf = "12345678901";
        AlterarClienteJsonRequest request = criarMockAlterarClienteJsonRequest();
        Cliente cliente = criarMockCliente();
        ClienteJsonResponse response = criarMockClienteJsonResponse();

        when(atualizarClientePorCpfUseCase.alterarClientePorCpf(eq(cpf), any())).thenReturn(cliente);

        ResponseEntity<ClienteJsonResponse> result = clienteController.alterarCliente(cpf, request);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
    }

    @Test
    void deveExcluirCliente() {
        String cpf = "12345678901";

        Mockito.doNothing().when(excluirClientePorCpfUseCase).excluirClientePorCpf(cpf);

        ResponseEntity<Void> result = clienteController.excluirCliente(cpf);

        assertEquals(204, result.getStatusCodeValue());
    }

    private ClienteJsonResponse criarMockClienteJsonResponse() {
        return new ClienteJsonResponse(
            "12345678901", // CPF
            "John Doe", // Name
            LocalDate.of(1985, 3, 25), // Date of Birth
            LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0),
            LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0),
            criarMockEnderecoJsonResponse()
        );
    }

    private EnderecoJsonResponse criarMockEnderecoJsonResponse() {
        return new EnderecoJsonResponse(
            UUID.fromString("a3059ff7-0aab-42b2-b062-0127c014ea14"), // ID
            "Street Name", // Rua
            123, // Número
            "12345-678", // CEP
            "Apartment 101", // Complemento
            "Downtown", // Bairro
            "City Name", // Cidade
            LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0),
            LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0)
        );
    }

    private ClienteJsonRequest criarMockClienteJsonRequest() {
        return new ClienteJsonRequest(
            "12345678901", // CPF
            "John Doe", // Name
            LocalDate.of(1985, 3, 25), // Date of Birth
            criarMockEnderecoJsonRequest() // Address
        );
    }

    private EnderecoJsonRequest criarMockEnderecoJsonRequest() {
        return new EnderecoJsonRequest(
            "Street Name", // Rua
            123, // Número
            "12345-678", // CEP
            "Apartment 101", // Complemento
            "Downtown", // Bairro
            "City Name" // Cidade
        );
    }

    private AlterarClienteJsonRequest criarMockAlterarClienteJsonRequest() {
        return new AlterarClienteJsonRequest(
            "12345678901", // CPF
            "John Doe", // Name
            LocalDate.of(1985, 3, 25), // Date of Birth
            criarMockAlterarEnderecoJsonRequest() // Address
        );
    }

    private AlterarEnderecoJsonRequest criarMockAlterarEnderecoJsonRequest() {
        return new AlterarEnderecoJsonRequest(
            UUID.fromString("a3059ff7-0aab-42b2-b062-0127c014ea14"), // ID
            "Street Name", // Rua
            123, // Número
            "12345-678", // CEP
            "Apartment 101", // Complemento
            "Downtown", // Bairro
            "City Name" // Cidade
        );
    }

    public Cliente criarMockCliente() {
        return Cliente.builder()
                .cpf("12345678901")
                .nome("John Doe")
                .dataNascimento(LocalDate.of(1985, 3, 25))
                .endereco(criarMockEndereco())
                .dataCriacao(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0))
                .dataUltimaAlteracao(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0))
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
                .dataCriacao(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0))
                .dataUltimaAlteracao(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0))
            .build();
    }
}