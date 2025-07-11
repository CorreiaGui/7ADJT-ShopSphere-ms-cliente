package br.com.fiap.ms.cliente.cliente.exception;

public class UnprocessableEntityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnprocessableEntityException(String errorMessage) {
        super(errorMessage);
    }
}