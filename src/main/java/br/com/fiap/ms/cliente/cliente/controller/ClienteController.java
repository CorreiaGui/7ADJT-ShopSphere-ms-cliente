package br.com.fiap.ms.cliente.cliente.controller;

import br.com.fiap.ms.cliente.cliente.controller.json.ClienteJson;
import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.usecase.BuscarClienteUseCase;
import br.com.fiap.ms.cliente.cliente.utils.ClienteUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/clientes",
                produces = "application/json")
public class ClienteController {

    private final BuscarClienteUseCase buscarClienteUseCase;

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteJson> buscarClientePorCpf(@PathVariable("cpf") String cpf) {
        log.info("Buscando cliente com CPF: {}", cpf);
        Cliente cliente = buscarClienteUseCase.buscarClientePorCpf(cpf);
        if (cliente == null) {
            log.error("Cliente não encontrado com CPF: {}", cpf);
            return ResponseEntity.notFound().build();
        }
        log.info("Cliente encontrado: {}", cliente);
        ClienteJson clienteJson = ClienteUtils.convertToClienteJson(cliente);
        log.info("clienteJson encontrado: {}", clienteJson);
        return ResponseEntity.ok(clienteJson);
    }
}
