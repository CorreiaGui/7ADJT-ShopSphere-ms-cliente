package br.com.fiap.ms.cliente.cliente.controller.handlers;

import br.com.fiap.ms.cliente.cliente.exception.ResourceExistingException;
import br.com.fiap.ms.cliente.cliente.exception.ResourceNotFoundException;
import br.com.fiap.ms.cliente.cliente.exception.UnprocessableEntityException;
import br.com.fiap.ms.cliente.cliente.exception.dto.ResourceExistingExceptionDTO;
import br.com.fiap.ms.cliente.cliente.exception.dto.ResourceNotFoundDTO;
import br.com.fiap.ms.cliente.cliente.exception.dto.UnprocessableEntityExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException e) {
        logger.error("ResourceNotFoundException ", e);
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<UnprocessableEntityExceptionDTO> handlerUnprocessableEntityException(UnprocessableEntityException e) {
        logger.error("UnprocessableEntityException ", e);
        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status.value()).body(new UnprocessableEntityExceptionDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(ResourceExistingException.class)
    public ResponseEntity<ResourceExistingExceptionDTO> handlerResourceExistingException(ResourceExistingException e) {
        logger.error("ResourceExistingException ", e);
        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status.value()).body(new ResourceExistingExceptionDTO(e.getMessage(), status.value()));
    }
}
