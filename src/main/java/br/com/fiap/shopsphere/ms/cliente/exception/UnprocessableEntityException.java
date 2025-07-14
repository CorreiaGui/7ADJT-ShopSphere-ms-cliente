package br.com.fiap.shopsphere.ms.cliente.exception;

public class UnprocessableEntityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnprocessableEntityException(String errorMessage) {
        super(errorMessage);
    }
}