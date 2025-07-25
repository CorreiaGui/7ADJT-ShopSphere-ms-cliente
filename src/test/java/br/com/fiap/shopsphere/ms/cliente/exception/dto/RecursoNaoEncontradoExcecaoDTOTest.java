package br.com.fiap.shopsphere.ms.cliente.exception.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursoNaoEncontradoExcecaoDTOTest {

    @Test
    void testRecordFields() {
        String expectedMessage = "Recurso não encontrado";
        int expectedStatusCode = 404;

        RecursoNaoEncontradoExcecaoDTO dto = new RecursoNaoEncontradoExcecaoDTO(expectedMessage, expectedStatusCode);

        assertEquals(expectedMessage, dto.errorMessage());
        assertEquals(expectedStatusCode, dto.statusCode());
    }

    @Test
    void testEqualsAndHashCode() {
        RecursoNaoEncontradoExcecaoDTO dto1 = new RecursoNaoEncontradoExcecaoDTO("Erro", 404);
        RecursoNaoEncontradoExcecaoDTO dto2 = new RecursoNaoEncontradoExcecaoDTO("Erro", 404);
        RecursoNaoEncontradoExcecaoDTO dto3 = new RecursoNaoEncontradoExcecaoDTO("Outro erro", 500);

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testToString() {
        RecursoNaoEncontradoExcecaoDTO dto = new RecursoNaoEncontradoExcecaoDTO("Erro", 404);
        String expected = "RecursoNaoEncontradoExcecaoDTO[errorMessage=Erro, statusCode=404]";
        assertEquals(expected, dto.toString());
    }
}
