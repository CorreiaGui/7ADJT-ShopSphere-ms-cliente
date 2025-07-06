package br.com.fiap.ms.cliente.cliente.controller;

import br.com.fiap.ms.cliente.cliente.controller.json.AtualizarClienteJsonRequest;
import br.com.fiap.ms.cliente.cliente.controller.json.ClienteJsonRequest;
import br.com.fiap.ms.cliente.cliente.controller.json.ClienteJsonResponse;
import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.usecase.*;
import br.com.fiap.ms.cliente.cliente.utils.ClienteUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fiap.ms.cliente.cliente.utils.ClienteUtils.convertToClienteJsonResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = ClienteController.V1_CLIENTES,
                produces = "application/json")
public class ClienteController {
    static final String V1_CLIENTES = "/api/v1/clientes";
    private final BuscarClientePorCpfUseCase buscarClientePorCpfUseCase;
    private final BuscarClientesUseCase buscarClientesUseCase;
    private final CriarClienteUseCase criarClienteUseCase;
    private final AlterarClientePorCpfUseCase atualizarClientePorCpfUseCase;
    private final ExcluirClientePorCpfUseCase excluirClientePorCpfUseCase;

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteJsonResponse> buscarClientePorCpf(@PathVariable("cpf") String cpf) {
        log.info("GET | {} | Iniciado busca de clientes pelo CPF | CPF: {}", V1_CLIENTES, cpf);
        Cliente cliente = buscarClientePorCpfUseCase.buscarClientePorCpf(cpf);
        if (cliente == null) {
            log.info("GET | {} | Iniciado busca de clientes pelo CPF | CPF: {}", V1_CLIENTES, cpf);
            return ResponseEntity.notFound().build();
        }
        log.info("Cliente encontrado: {}", cliente);
        ClienteJsonResponse clienteJsonResponse = convertToClienteJsonResponse(cliente);
        log.info("clienteJson encontrado: {}", clienteJsonResponse);
        return ResponseEntity.ok(clienteJsonResponse);
    }

    @GetMapping
    public ResponseEntity<List<ClienteJsonResponse>> buscarClientes(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("GET | {} | Iniciado busca de clientes com paginacao | page: {} size: {} ", V1_CLIENTES, page, size);
        List<Cliente> clientes = buscarClientesUseCase.buscarClientes(page, size);
        log.info("GET | {} | Finalizada busca de clientes com paginacao | page: {} size: {} clientes: {}", V1_CLIENTES, page, size, clientes);
        List<ClienteJsonResponse> clientesJson = clientes.stream().map(ClienteUtils::convertToClienteJsonResponse).toList();
        return ResponseEntity.ok(clientesJson);
    }

    @PostMapping
    public ResponseEntity<ClienteJsonResponse> criarCliente(@RequestBody ClienteJsonRequest clienteJsonRequest) {
        log.info("POST | {} | Iniciado criação de cliente | clienteJson: {}", V1_CLIENTES, clienteJsonRequest);
        Cliente cliente = ClienteUtils.convertToCliente(clienteJsonRequest);
        Cliente novoCliente = criarClienteUseCase.criarCliente(cliente);
        log.info("Cliente criado com sucesso: {}", novoCliente);
        return ResponseEntity.status(201).body(convertToClienteJsonResponse(novoCliente));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteJsonResponse> alterarCliente(@PathVariable("cpf") String cpf, @RequestBody AtualizarClienteJsonRequest clienteJsonRequest) {
        log.info("POST | {} | Iniciado alteracao de cliente | clienteJson: {}", V1_CLIENTES, clienteJsonRequest);
        Cliente cliente = ClienteUtils.convertToCliente(clienteJsonRequest);
        Cliente clienteAtualizado = atualizarClientePorCpfUseCase.alterarClientePorCpf(cpf, cliente);
        log.info("Cliente alterado com sucesso: {}", clienteAtualizado);
        return ResponseEntity.status(201).body(convertToClienteJsonResponse(clienteAtualizado));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> excluirCliente(@PathVariable("cpf") String cpf) {
        log.info("GET | {} | Iniciada exclusao de cliente pelo CPF | CPF: {}", V1_CLIENTES, cpf);
        excluirClientePorCpfUseCase.excluirClientePorCpf(cpf);
        log.info("GET | {} | Finalizada exclusao de cliente pelo CPF | CPF: {}", V1_CLIENTES, cpf);
        return ResponseEntity.ok().build();
    }
}
