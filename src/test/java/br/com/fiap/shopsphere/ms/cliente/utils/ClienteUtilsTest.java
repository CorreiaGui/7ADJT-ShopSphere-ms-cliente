package br.com.fiap.shopsphere.ms.cliente.utils;

import br.com.fiap.shopsphere.ms.cliente.controller.json.*;
import br.com.fiap.shopsphere.ms.cliente.domain.Cliente;
import br.com.fiap.shopsphere.ms.cliente.domain.Endereco;
import br.com.fiap.shopsphere.ms.cliente.exception.UnprocessableEntityException;
import br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.entity.ClienteEntity;
import br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.entity.EnderecoEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClienteUtilsTest {

    @Test
    void testConvertToClienteFromOptional() {
        var clienteOptionalEntity = criarMockOptionalClienteEntity();

        Optional<Cliente> cliente = ClienteUtils.convertToCliente(clienteOptionalEntity);

        assertTrue(cliente.isPresent());
        assertEquals("12345678901", cliente.get().getCpf());
        assertEquals("John Doe", cliente.get().getNome());
        assertEquals(LocalDate.of(1985, 3, 25), cliente.get().getDataNascimento());
        assertEquals(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0), cliente.get().getDataCriacao());
        assertEquals(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0), cliente.get().getDataUltimaAlteracao());
        Endereco endereco = cliente.get().getEndereco();
        assertNotNull(endereco);
        assertEquals(UUID.fromString("a3059ff7-0aab-42b2-b062-0127c014ea14"), endereco.getId());
        assertEquals("Street Name", endereco.getRua());
        assertEquals(123, endereco.getNumero());
        assertEquals("12345-678", endereco.getCep());
        assertEquals("Apartment 101", endereco.getComplemento());
        assertEquals("Downtown", endereco.getBairro());
        assertEquals("City Name", endereco.getCidade());
        assertEquals(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0), endereco.getDataCriacao());
        assertEquals(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0), endereco.getDataUltimaAlteracao());

        // Scenario 2: Optional is empty
        Optional<ClienteEntity> emptyOptional = Optional.empty();
        Optional<Cliente> emptyCliente = ClienteUtils.convertToCliente(emptyOptional);
        assertFalse(emptyCliente.isPresent());
    }

    @Test
    void testConvertToClienteFromEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf("12345678901");
        clienteEntity.setNome("John Doe");
        clienteEntity.setDataNascimento(LocalDate.of(1985, 3, 25));
        clienteEntity.setDataCriacao(LocalDateTime.now());
        clienteEntity.setDataUltimaAlteracao(LocalDateTime.now());

        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(UUID.randomUUID());
        enderecoEntity.setRua("Street Name");
        enderecoEntity.setNumero(123);
        enderecoEntity.setCep("12345-678");
        enderecoEntity.setComplemento("Apartment 101");
        enderecoEntity.setBairro("Downtown");
        enderecoEntity.setCidade("City Name");
        enderecoEntity.setDataCriacao(LocalDateTime.now());
        enderecoEntity.setDataUltimaAlteracao(LocalDateTime.now());

        clienteEntity.setEndereco(enderecoEntity);

        Cliente cliente = ClienteUtils.convertToCliente(clienteEntity);

        assertNotNull(cliente);
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("John Doe", cliente.getNome());
        assertEquals("Street Name", cliente.getEndereco().getRua());
    }

    @Test
    void testConvertToClienteJsonResponse() {
        Endereco endereco = Endereco.builder()
                .id(UUID.randomUUID())
                .rua("Street Name")
                .numero(123)
                .cep("12345-678")
                .complemento("Apartment 101")
                .bairro("Downtown")
                .cidade("City Name")
                .dataCriacao(LocalDateTime.now())
                .dataUltimaAlteracao(LocalDateTime.now())
            .build();

        Cliente cliente = Cliente.builder()
                .cpf("12345678901")
                .nome("John Doe")
                .dataNascimento(LocalDate.of(1985, 3, 25))
                .endereco(endereco)
                .dataCriacao(LocalDateTime.now())
                .dataUltimaAlteracao(LocalDateTime.now())
            .build();

        ClienteJsonResponse response = ClienteUtils.convertToClienteJsonResponse(cliente);

        assertNotNull(response);
        assertEquals("12345678901", response.cpf());
        assertEquals("John Doe", response.nome());
        //assertEquals("Street Name", response.enderecoJsonResponse().rua());
    }

    @Test
    void testUuidValidatorValid() {
        UUID validUuid = UUID.randomUUID();
        assertDoesNotThrow(() -> ClienteUtils.uuidValidator(validUuid));
    }

    @Test
    void testUuidValidatorInvalid() {
        UUID invalidUuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
        Exception exception = assertThrows(UnprocessableEntityException.class, () -> ClienteUtils.uuidValidator(invalidUuid));
        assertEquals("ID inválido.", exception.getMessage());
    }

    private Optional<ClienteEntity> criarMockOptionalClienteEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf("12345678901");
        clienteEntity.setNome("John Doe");
        clienteEntity.setDataNascimento(LocalDate.of(1985, 3, 25));
        clienteEntity.setDataCriacao(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0));
        clienteEntity.setDataUltimaAlteracao(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0));

        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(UUID.fromString("a3059ff7-0aab-42b2-b062-0127c014ea14"));
        enderecoEntity.setRua("Street Name");
        enderecoEntity.setNumero(123);
        enderecoEntity.setCep("12345-678");
        enderecoEntity.setComplemento("Apartment 101");
        enderecoEntity.setBairro("Downtown");
        enderecoEntity.setCidade("City Name");
        enderecoEntity.setDataCriacao(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0));
        enderecoEntity.setDataUltimaAlteracao(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0));
        clienteEntity.setEndereco(enderecoEntity);

        return Optional.of(clienteEntity);
    }

    private Optional<Cliente> criarMockOptionalCliente() {
        Endereco endereco = Endereco.builder()
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

        Cliente cliente = Cliente.builder()
                .cpf("12345678901")
                .nome("John Doe")
                .dataNascimento(LocalDate.of(1985, 3, 25))
                .endereco(endereco)
                .dataCriacao(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0))
                .dataUltimaAlteracao(LocalDateTime.of(2025, 1, 1, 1, 1, 1, 0))
            .build();

        return Optional.of(cliente);
    }

    @Test
    void testConvertToClienteFromEntityNull() {
        Cliente cliente = ClienteUtils.convertToCliente((ClienteEntity) null);
        assertNull(cliente);
    }

    @Test
    void testConvertToEnderecoEntityWithNullEnderecoEntity() {
        Endereco endereco = Endereco.builder()
                .rua("Rua Exemplo")
                .numero(100)
                .cep("12345-678")
                .complemento("Apto 10")
                .bairro("Centro")
                .cidade("Cidade Exemplo")
                .build();

        EnderecoEntity entity = ClienteUtils.convertToEnderecoEntity(endereco, null);

        assertNotNull(entity);
        assertEquals("Rua Exemplo", entity.getRua());
        assertNotNull(entity.getDataCriacao());
        assertNull(entity.getId());
    }

    @Test
    void testConvertToClienteEntityWithNullClienteEntity() {
        Cliente cliente = criarMockOptionalCliente().get();

        ClienteEntity result = ClienteUtils.convertToClienteEntity(cliente, null);

        assertNotNull(result);
        assertEquals(cliente.getCpf(), result.getCpf());
        assertNotNull(result.getDataCriacao());
        assertNotNull(result.getEndereco());
    }

    @Test
    void testConvertToClienteFromClienteJsonRequest() {
        EnderecoJsonRequest enderecoJson = new EnderecoJsonRequest(
                 "Rua Exemplo", 100, "12345-678", "Apto 10", "Centro", "Cidade Exemplo");

        ClienteJsonRequest request = new ClienteJsonRequest("12345678901", "Fulano", LocalDate.of(1990, 1, 1), enderecoJson);

        Cliente cliente = ClienteUtils.convertToCliente(request);

        assertNotNull(cliente);
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("Fulano", cliente.getNome());
        assertEquals("Rua Exemplo", cliente.getEndereco().getRua());
    }

    @Test
    void testConvertToClienteFromAlterarClienteJsonRequest() {
        AlterarEnderecoJsonRequest enderecoJson = new AlterarEnderecoJsonRequest(
                UUID.randomUUID(), "Rua Exemplo", 100, "12345-678", "Apto 10", "Centro", "Cidade Exemplo");

        AlterarClienteJsonRequest request = new AlterarClienteJsonRequest("12345678901", "Fulano", LocalDate.of(1990, 1, 1), enderecoJson);

        Cliente cliente = ClienteUtils.convertToCliente(request);

        assertNotNull(cliente);
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("Fulano", cliente.getNome());
        assertEquals("Rua Exemplo", cliente.getEndereco().getRua());
    }


}