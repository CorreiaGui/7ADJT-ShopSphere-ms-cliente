package br.com.fiap.shopsphere.ms.cliente.usecase;

import br.com.fiap.shopsphere.ms.cliente.gateway.ClienteGateway;
import br.com.fiap.shopsphere.ms.cliente.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExcluirClientePorCpfUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ExcluirClientePorCpfUseCase excluirClientePorCpfUseCase;

    @Test
    void deveExcluirClienteQuandoCpfExistir() {
        String cpf = "12345678901";
        doNothing().when(clienteGateway).excluirClientePorCpf(cpf);

        excluirClientePorCpfUseCase.excluirClientePorCpf(cpf);

        verify(clienteGateway, times(1)).excluirClientePorCpf(cpf);
    }

    @Test
    void deveLancarExcecaoQuandoCpfNaoExistir() {
        String cpf = "12345678901";
        doThrow(new RecursoNaoEncontradoException("Cliente não encontrado")).when(clienteGateway).excluirClientePorCpf(cpf);

        assertThrows(RecursoNaoEncontradoException.class, () -> {
            excluirClientePorCpfUseCase.excluirClientePorCpf(cpf);
        });

        verify(clienteGateway, times(1)).excluirClientePorCpf(cpf);
    }
}