package br.com.fiap.shopsphere.ms.cliente.exception.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursoExistenteExcecaoDTOTest {

    @Test
    void testFieldValues() {
        String message = "Recurso já existe";
        int status = 409;

        RecursoExistenteExcecaoDTO dto = new RecursoExistenteExcecaoDTO(message, status);

        assertEquals(message, dto.errorMessage());
        assertEquals(status, dto.statusCode());
    }

    @Test
    void testEqualsAndHashCode() {
        RecursoExistenteExcecaoDTO dto1 = new RecursoExistenteExcecaoDTO("Erro", 409);
        RecursoExistenteExcecaoDTO dto2 = new RecursoExistenteExcecaoDTO("Erro", 409);
        RecursoExistenteExcecaoDTO dto3 = new RecursoExistenteExcecaoDTO("Outro erro", 400);

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testToString() {
        RecursoExistenteExcecaoDTO dto = new RecursoExistenteExcecaoDTO("Erro", 409);
        String expected = "RecursoExistenteExcecaoDTO[errorMessage=Erro, statusCode=409]";
        assertEquals(expected, dto.toString());
    }
}
