package br.com.fiap.shopsphere.ms.cliente.exception;

public class RecursoExistenteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecursoExistenteException(String errorMessage) {
        super(errorMessage);
    }
}