package br.com.fiap.shopsphere.ms.cliente.exception.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnprocessableEntityExceptionDTOTest {

    @Test
    void testFieldValues() {
        String message = "Entidade não processável";
        int status = 422;

        UnprocessableEntityExceptionDTO dto = new UnprocessableEntityExceptionDTO(message, status);

        assertEquals(message, dto.errorMessage());
        assertEquals(status, dto.statusCode());
    }

    @Test
    void testEqualityAndHashCode() {
        UnprocessableEntityExceptionDTO dto1 = new UnprocessableEntityExceptionDTO("Erro", 422);
        UnprocessableEntityExceptionDTO dto2 = new UnprocessableEntityExceptionDTO("Erro", 422);
        UnprocessableEntityExceptionDTO dto3 = new UnprocessableEntityExceptionDTO("Outro erro", 400);

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testToStringMethod() {
        UnprocessableEntityExceptionDTO dto = new UnprocessableEntityExceptionDTO("Erro", 422);
        String expected = "UnprocessableEntityExceptionDTO[errorMessage=Erro, statusCode=422]";
        assertEquals(expected, dto.toString());
    }
}
