package br.com.fiap.shopsphere.ms.cliente.usecase;

import br.com.fiap.shopsphere.ms.cliente.domain.Cliente;
import br.com.fiap.shopsphere.ms.cliente.gateway.ClienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarClientesUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private BuscarClientesUseCase buscarClientesUseCase;

    @Test
    void deveRetornarListaDeClientes() {
        List<Cliente> clientesEsperados = List.of(new Cliente(), new Cliente());
        when(clienteGateway.buscarClientes(anyInt(), anyInt())).thenReturn(clientesEsperados);

        List<Cliente> clientes = buscarClientesUseCase.buscarClientes(0, 2);

        assertEquals(clientesEsperados, clientes);
        verify(clienteGateway, times(1)).buscarClientes(0, 2);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremClientes() {
        when(clienteGateway.buscarClientes(anyInt(), anyInt())).thenReturn(Collections.emptyList());

        List<Cliente> clientes = buscarClientesUseCase.buscarClientes(0, 2);

        assertEquals(Collections.emptyList(), clientes);
        verify(clienteGateway, times(1)).buscarClientes(0, 2);
    }
}