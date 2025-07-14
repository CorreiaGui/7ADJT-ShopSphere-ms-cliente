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
public class AlterarClientePorCpfUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private AlterarClientePorCpfUseCase alterarClientePorCpfUseCase;

    @Test
    void deveAlterarClienteQuandoCpfExistir() {
        String cpf = "12345678901";
        Cliente clienteAtualizado = new Cliente();
        when(clienteGateway.alterarClientePorCpf(cpf, clienteAtualizado)).thenReturn(Optional.of(clienteAtualizado));

        Cliente cliente = alterarClientePorCpfUseCase.alterarClientePorCpf(cpf, clienteAtualizado);

        assertEquals(clienteAtualizado, cliente);
        verify(clienteGateway, times(1)).alterarClientePorCpf(cpf, clienteAtualizado);
    }

    @Test
    void deveLancarExcecaoQuandoCpfNaoExistir() {
        String cpf = "12345678901";
        Cliente clienteAtualizado = new Cliente();
        when(clienteGateway.alterarClientePorCpf(anyString(), any())).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> {
            alterarClientePorCpfUseCase.alterarClientePorCpf(cpf, clienteAtualizado);
        });

        verify(clienteGateway, times(1)).alterarClientePorCpf(anyString(), any(Cliente.class));
    }
}