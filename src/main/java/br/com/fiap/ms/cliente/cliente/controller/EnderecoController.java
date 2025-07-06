package br.com.fiap.ms.cliente.cliente.controller;

import br.com.fiap.ms.cliente.cliente.controller.json.ClienteJsonRequest;
import br.com.fiap.ms.cliente.cliente.controller.json.ClienteJsonResponse;
import br.com.fiap.ms.cliente.cliente.controller.json.EnderecoJsonRequest;
import br.com.fiap.ms.cliente.cliente.controller.json.EnderecoJsonResponse;
import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.domain.Endereco;
import br.com.fiap.ms.cliente.cliente.usecase.*;
import br.com.fiap.ms.cliente.cliente.utils.ClienteUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.ms.cliente.cliente.utils.ClienteUtils.convertToEnderecoJsonResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = EnderecoController.V1_ENDERECOS,
                produces = "application/json")
public class EnderecoController {
    static final String V1_ENDERECOS = "/api/v1/enderecos";
    private final BuscarEnderecoPorIdUseCase buscarEnderecoPorIdUseCase;
    private final BuscarEnderecosUseCase buscarEnderecosUseCase;
    private final CriarEnderecoUseCase criarEnderecoUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoJsonResponse> buscarEnderecoPorId(@PathVariable("id") UUID id) {
        log.info("GET | {} | Iniciado busca de clientes pelo ID | ID: {}", V1_ENDERECOS, id);
        Endereco endereco = buscarEnderecoPorIdUseCase.buscarEnderecoPorId(id);
        if (endereco == null) {
            log.info("GET | {} | Iniciado busca de enderecos pelo ID | ID: {}", V1_ENDERECOS, id);
            return ResponseEntity.notFound().build();
        }
        log.info("Endereco encontrado: {}", endereco);
        EnderecoJsonResponse enderecoJsonResponse = convertToEnderecoJsonResponse(endereco);
        return ResponseEntity.ok(enderecoJsonResponse);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoJsonResponse>> buscarEnderecos(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("GET | {} | Iniciado busca de enderecos com paginacao | page: {} size: {} ", V1_ENDERECOS, page, size);
        List<Endereco> enderecos = buscarEnderecosUseCase.buscarEnderecos(page, size);
        log.info("GET | {} | Finalizada busca de enderecos com paginacao | page: {} size: {} enderecos: {}", V1_ENDERECOS, page, size, enderecos);
        List<EnderecoJsonResponse> enderecosJsonResponse = enderecos.stream().map(ClienteUtils::convertToEnderecoJsonResponse).toList();
        return ResponseEntity.ok(enderecosJsonResponse);
    }

    @PostMapping
    public ResponseEntity<EnderecoJsonResponse> criarEndereco(@RequestBody EnderecoJsonRequest enderecoJsonRequest) {
        log.info("POST | {} | Iniciado criação de endereco | enderecoJsonRequest: {}", V1_ENDERECOS, enderecoJsonRequest);
        Endereco endereco = ClienteUtils.convertToEndereco(enderecoJsonRequest);
        Endereco novoEndereco = criarEnderecoUseCase.criarEndereco(endereco);
        log.info("Endereco criado com sucesso: {}", novoEndereco);
        return ResponseEntity.status(201).body(convertToEnderecoJsonResponse(novoEndereco));
    }
}
