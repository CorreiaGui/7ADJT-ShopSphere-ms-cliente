package br.com.fiap.shopsphere.ms.cliente.usecase;

import br.com.fiap.shopsphere.ms.cliente.domain.Cliente;
import br.com.fiap.shopsphere.ms.cliente.exception.RecursoNaoEncontradoException;
import br.com.fiap.shopsphere.ms.cliente.gateway.ClienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarClientePorCpfUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private BuscarClientePorCpfUseCase buscarClientePorCpfUseCase;

    @Test
    void deveRetornarClienteQuandoCpfExistir() {
        String cpf = "12345678901";
        Cliente clienteEsperado = new Cliente();
        when(clienteGateway.buscarClientePorCpf(cpf)).thenReturn(Optional.of(clienteEsperado));

        Cliente cliente = buscarClientePorCpfUseCase.buscarClientePorCpf(cpf);

        assertEquals(clienteEsperado, cliente);
        verify(clienteGateway, times(1)).buscarClientePorCpf(cpf);
    }

    @Test
    void deveLancarExcecaoQuandoCpfNaoExistir() {
        String cpf = "12345678901";
        when(clienteGateway.buscarClientePorCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> {
            buscarClientePorCpfUseCase.buscarClientePorCpf(cpf);
        });

        verify(clienteGateway, times(1)).buscarClientePorCpf(cpf);
    }
}