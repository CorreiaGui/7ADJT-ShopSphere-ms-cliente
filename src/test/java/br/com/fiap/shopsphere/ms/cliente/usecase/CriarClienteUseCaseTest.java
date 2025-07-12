package br.com.fiap.shopsphere.ms.cliente.usecase;

import br.com.fiap.shopsphere.ms.cliente.domain.Cliente;
import br.com.fiap.shopsphere.ms.cliente.gateway.ClienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CriarClienteUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private CriarClienteUseCase criarClienteUseCase;

    @Test
    void deveCriarClienteComSucesso() {
        Cliente cliente = new Cliente();
        Optional<Cliente> clienteEsperado = Optional.of(cliente);

        when(clienteGateway.criarCliente(cliente)).thenReturn(clienteEsperado);

        Optional<Cliente> clienteCriado = criarClienteUseCase.criarCliente(cliente);

        assertEquals(clienteEsperado, clienteCriado);
        verify(clienteGateway, times(1)).criarCliente(cliente);
    }

    @Test
    void deveRetornarVazioQuandoClienteNaoForCriado() {
        Cliente cliente = new Cliente();

        when(clienteGateway.criarCliente(cliente)).thenReturn(Optional.empty());

        Optional<Cliente> clienteCriado = criarClienteUseCase.criarCliente(cliente);

        assertEquals(Optional.empty(), clienteCriado);
        verify(clienteGateway, times(1)).criarCliente(cliente);
    }
}