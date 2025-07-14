package br.com.fiap.shopsphere.ms.cliente.controller.handlers;

import br.com.fiap.shopsphere.ms.cliente.exception.RecursoExistenteException;
import br.com.fiap.shopsphere.ms.cliente.exception.RecursoNaoEncontradoException;
import br.com.fiap.shopsphere.ms.cliente.exception.UnprocessableEntityException;
import br.com.fiap.shopsphere.ms.cliente.exception.dto.RecursoExistenteExcecaoDTO;
import br.com.fiap.shopsphere.ms.cliente.exception.dto.RecursoNaoEncontradoExcecaoDTO;
import br.com.fiap.shopsphere.ms.cliente.exception.dto.UnprocessableEntityExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<RecursoNaoEncontradoExcecaoDTO> handlerResourceNotFoundException(RecursoNaoEncontradoException e) {
        logger.error("ResourceNotFoundException ", e);
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new RecursoNaoEncontradoExcecaoDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<UnprocessableEntityExceptionDTO> handlerUnprocessableEntityException(UnprocessableEntityException e) {
        logger.error("UnprocessableEntityException ", e);
        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status.value()).body(new UnprocessableEntityExceptionDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(RecursoExistenteException.class)
    public ResponseEntity<RecursoExistenteExcecaoDTO> handlerResourceExistingException(RecursoExistenteException e) {
        logger.error("ResourceExistingException ", e);
        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status.value()).body(new RecursoExistenteExcecaoDTO(e.getMessage(), status.value()));
    }
}
