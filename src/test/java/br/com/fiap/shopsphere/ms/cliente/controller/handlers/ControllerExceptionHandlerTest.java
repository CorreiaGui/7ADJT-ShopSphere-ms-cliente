package br.com.fiap.shopsphere.ms.cliente.controller.handlers;

import br.com.fiap.shopsphere.ms.cliente.exception.RecursoExistenteException;
import br.com.fiap.shopsphere.ms.cliente.exception.RecursoNaoEncontradoException;
import br.com.fiap.shopsphere.ms.cliente.exception.UnprocessableEntityException;
import br.com.fiap.shopsphere.ms.cliente.exception.dto.RecursoExistenteExcecaoDTO;
import br.com.fiap.shopsphere.ms.cliente.exception.dto.RecursoNaoEncontradoExcecaoDTO;
import br.com.fiap.shopsphere.ms.cliente.exception.dto.UnprocessableEntityExceptionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ControllerExceptionHandler();
    }

    @Test
    void testHandlerResourceNotFoundException() {
        String errorMsg = "Cliente não encontrado";
        RecursoNaoEncontradoException exception = new RecursoNaoEncontradoException(errorMsg);

        ResponseEntity<RecursoNaoEncontradoExcecaoDTO> response = handler.handlerResourceNotFoundException(exception);

        assertEquals(404, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(errorMsg, response.getBody().errorMessage());
        assertEquals(404, response.getBody().statusCode());
    }

    @Test
    void testHandlerUnprocessableEntityException() {
        String errorMsg = "CPF inválido";
        UnprocessableEntityException exception = new UnprocessableEntityException(errorMsg);

        ResponseEntity<UnprocessableEntityExceptionDTO> response = handler.handlerUnprocessableEntityException(exception);

        assertEquals(422, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(errorMsg, response.getBody().errorMessage());
        assertEquals(422, response.getBody().statusCode());
    }

    @Test
    void testHandlerResourceExistingException() {
        String errorMsg = "Cliente já existe";
        RecursoExistenteException exception = new RecursoExistenteException(errorMsg);

        ResponseEntity<RecursoExistenteExcecaoDTO> response = handler.handlerResourceExistingException(exception);

        assertEquals(422, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(errorMsg, response.getBody().errorMessage());
        assertEquals(422, response.getBody().statusCode());
    }
}
