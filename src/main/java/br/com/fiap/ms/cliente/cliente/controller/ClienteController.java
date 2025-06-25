package br.com.fiap.ms.cliente.cliente.controller;

import br.com.fiap.ms.cliente.cliente.controller.json.ClienteJson;
import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.usecase.BuscarClientePorCpfUseCase;
import br.com.fiap.ms.cliente.cliente.usecase.BuscarClientesUseCase;
import br.com.fiap.ms.cliente.cliente.utils.ClienteUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fiap.ms.cliente.cliente.utils.ClienteUtils.convertToClienteJson;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = ClienteController.V1_CLIENTES,
                produces = "application/json")
public class ClienteController {

    static final String V1_CLIENTES = "/api/v1/clientes";
    private final BuscarClientePorCpfUseCase buscarClientePorCpfUseCase;
    private final BuscarClientesUseCase buscarClientesUseCase;

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteJson> buscarClientePorCpf(@PathVariable("cpf") String cpf) {
        log.info("GET | {} | Iniciado busca de clientes pelo CPF | CPF: {}", V1_CLIENTES, cpf);
        Cliente cliente = buscarClientePorCpfUseCase.buscarClientePorCpf(cpf);
        if (cliente == null) {
            log.info("GET | {} | Iniciado busca de clientes pelo CPF | CPF: {}", V1_CLIENTES, cpf);
            return ResponseEntity.notFound().build();
        }
        log.info("Cliente encontrado: {}", cliente);
        ClienteJson clienteJson = convertToClienteJson(cliente);
        log.info("clienteJson encontrado: {}", clienteJson);
        return ResponseEntity.ok(clienteJson);
    }

    @GetMapping
    public ResponseEntity<List<ClienteJson>> buscarClientes(@RequestParam(value = "page", defaultValue = "0") int page,
                                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("GET | {} | Iniciado busca de clientes com paginacao | page: {} size: {} ", V1_CLIENTES, page, size);
        List<Cliente> clientes = buscarClientesUseCase.buscarClientes(page, size);
        log.info("GET | {} | Finalizada busca de clientes com paginacao | page: {} size: {} clientes: {}", V1_CLIENTES, page, size, clientes);
        List<ClienteJson> clientesJson = clientes.stream().map(ClienteUtils::convertToClienteJson).toList();
        return ResponseEntity.ok(clientesJson);
    }
}
