package br.com.fiap.ms.cliente.cliente.exception;

public class ResourceExistingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceExistingException(String errorMessage) {
        super(errorMessage);
    }
}