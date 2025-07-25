package br.com.fiap.shopsphere.ms.cliente.controller;

import br.com.fiap.shopsphere.ms.cliente.controller.json.AlterarClienteJsonRequest;
import br.com.fiap.shopsphere.ms.cliente.controller.json.ClienteJsonRequest;
import br.com.fiap.shopsphere.ms.cliente.controller.json.ClienteJsonResponse;
import br.com.fiap.shopsphere.ms.cliente.domain.Cliente;
import br.com.fiap.shopsphere.ms.cliente.usecase.*;
import br.com.fiap.shopsphere.ms.cliente.utils.ClienteUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import static br.com.fiap.shopsphere.ms.cliente.utils.ClienteUtils.convertToClienteJsonResponse;

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
        log.info("GET | {} | Iniciada busca de clientes pelo CPF | CPF: {}", V1_CLIENTES, cpf);
        Cliente cliente = buscarClientePorCpfUseCase.buscarClientePorCpf(cpf);
        ClienteJsonResponse clienteJsonResponse = convertToClienteJsonResponse(cliente);
        log.info("GET | {} | Finalizada busca de clientes pelo CPF | CPF: {} {}", V1_CLIENTES, cpf, clienteJsonResponse);
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
    public ResponseEntity<ClienteJsonResponse> criarCliente(@Valid @RequestBody ClienteJsonRequest clienteJsonRequest) {
        log.info("POST | {} | Iniciada criação de cliente | request: {}", V1_CLIENTES, clienteJsonRequest);
        Cliente cliente = ClienteUtils.convertToCliente(clienteJsonRequest);
        Optional<Cliente> novoCliente = criarClienteUseCase.criarCliente(cliente);
        ClienteJsonResponse clienteJsonResponse = convertToClienteJsonResponse(novoCliente.get());
        log.info("POST | {} | Finalizada criação de cliente | response: {}", V1_CLIENTES, clienteJsonResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteJsonResponse);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteJsonResponse> alterarCliente(@PathVariable("cpf") String cpf, @RequestBody AlterarClienteJsonRequest clienteJsonRequest) {
        log.info("PUT | {} | Iniciada alteracao de cliente | request: {}", V1_CLIENTES, clienteJsonRequest);
        Cliente cliente = ClienteUtils.convertToCliente(clienteJsonRequest);
        Cliente clienteAtualizado = atualizarClientePorCpfUseCase.alterarClientePorCpf(cpf, cliente);
        ClienteJsonResponse clienteJsonResponse = convertToClienteJsonResponse(clienteAtualizado);
        log.info("PUT | {} | Finalizada alteracao de cliente | response: {}", V1_CLIENTES, clienteJsonResponse);
        return ResponseEntity.status(HttpStatus.OK).body(clienteJsonResponse);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> excluirCliente(@PathVariable("cpf") String cpf) {
        log.info("GET | {} | Iniciada exclusao de cliente pelo CPF | CPF: {}", V1_CLIENTES, cpf);
        excluirClientePorCpfUseCase.excluirClientePorCpf(cpf);
        log.info("GET | {} | Finalizada exclusao de cliente pelo CPF | CPF: {}", V1_CLIENTES, cpf);
        return ResponseEntity.noContent().build();
    }
}
