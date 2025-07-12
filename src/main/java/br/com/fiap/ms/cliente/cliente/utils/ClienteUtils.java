package br.com.fiap.ms.cliente.cliente.utils;

import br.com.fiap.ms.cliente.cliente.controller.json.*;
import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.domain.Endereco;
import br.com.fiap.ms.cliente.cliente.exception.ResourceNotFoundException;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.entity.ClienteEntity;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.entity.EnderecoEntity;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.ms.cliente.cliente.utils.ClienteConstants.ID_INVALIDO;
import static java.util.regex.Pattern.matches;

public class ClienteUtils {

    private ClienteUtils() {}
    private static final String REGEX_UUID = "^[0-9a-f]{8}-[0-9a-f]{4}-[4][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";

    public static Optional<Cliente> convertToCliente(Optional<ClienteEntity> clienteEntity) {
        if(clienteEntity.isPresent()) {
            return Optional.of(
                Cliente.builder()
                    .cpf(clienteEntity.get().getCpf())
                    .nome(clienteEntity.get().getNome())
                    .dataNascimento(clienteEntity.get().getDataNascimento())
                    .endereco(convertToEndereco(Optional.ofNullable(clienteEntity.get().getEndereco())))
                    .dataCriacao(clienteEntity.get().getDataCriacao())
                    .dataUltimaAlteracao(clienteEntity.get().getDataUltimaAlteracao())
                .build()
            );
        }
        return Optional.empty();
    }

    public static Cliente convertToCliente(ClienteEntity clienteEntity) {
        if (clienteEntity != null) {
            return Cliente.builder()
                    .cpf(clienteEntity.getCpf())
                    .nome(clienteEntity.getNome())
                    .dataNascimento(clienteEntity.getDataNascimento())
                    .endereco(convertToEndereco(Optional.ofNullable(clienteEntity.getEndereco())))
                    .dataCriacao(clienteEntity.getDataCriacao())
                    .dataUltimaAlteracao(clienteEntity.getDataUltimaAlteracao())
                .build();
        }
        return null;
    }

    public static Cliente convertToCliente(ClienteJsonRequest clienteJsonRequest) {
        Endereco endereco = Endereco.builder()
                .rua(clienteJsonRequest.enderecoJsonRequest().rua())
                .numero(clienteJsonRequest.enderecoJsonRequest().numero())
                .cep(clienteJsonRequest.enderecoJsonRequest().cep())
                .complemento(clienteJsonRequest.enderecoJsonRequest().complemento())
                .bairro(clienteJsonRequest.enderecoJsonRequest().bairro())
                .cidade(clienteJsonRequest.enderecoJsonRequest().cidade())
            .build();
        return Cliente.builder()
                .cpf(clienteJsonRequest.cpf())
                .nome(clienteJsonRequest.nome())
                .dataNascimento(clienteJsonRequest.dataNascimento())
                .endereco(endereco)
            .build();
    }

    public static Cliente convertToCliente(AlterarClienteJsonRequest clienteJsonRequest) {
        Endereco endereco = Endereco.builder()
                .id(clienteJsonRequest.enderecoJsonRequest().id())
                .rua(clienteJsonRequest.enderecoJsonRequest().rua())
                .numero(clienteJsonRequest.enderecoJsonRequest().numero())
                .cep(clienteJsonRequest.enderecoJsonRequest().cep())
                .complemento(clienteJsonRequest.enderecoJsonRequest().complemento())
                .bairro(clienteJsonRequest.enderecoJsonRequest().bairro())
                .cidade(clienteJsonRequest.enderecoJsonRequest().cidade())
            .build();
        return Cliente.builder()
                .cpf(clienteJsonRequest.cpf())
                .nome(clienteJsonRequest.nome())
                .dataNascimento(clienteJsonRequest.dataNascimento())
                .endereco(endereco)
                .build();
    }

    public static Endereco convertToEndereco(EnderecoJsonRequest enderecoJsonRequest) {
        return Endereco.builder()
                .rua(enderecoJsonRequest.rua())
                .numero(enderecoJsonRequest.numero())
                .cep(enderecoJsonRequest.cep())
                .complemento(enderecoJsonRequest.complemento())
                .bairro(enderecoJsonRequest.bairro())
                .cidade(enderecoJsonRequest.cidade())
            .build();
    }

    public static Endereco convertToEndereco(Optional<EnderecoEntity> enderecoEntity) {
        if(enderecoEntity.isPresent()) {
            return Endereco.builder()
                    .id(enderecoEntity.get().getId())
                    .rua(enderecoEntity.get().getRua())
                    .numero(enderecoEntity.get().getNumero())
                    .bairro(enderecoEntity.get().getBairro())
                    .cidade(enderecoEntity.get().getCidade())
                    .cep(enderecoEntity.get().getCep())
                    .complemento(enderecoEntity.get().getComplemento())
                    .dataCriacao(enderecoEntity.get().getDataCriacao())
                    .dataUltimaAlteracao(enderecoEntity.get().getDataUltimaAlteracao())
                .build();
        }
        return null;
    }

    public static Endereco convertToEndereco(EnderecoEntity enderecoEntity) {
        if (enderecoEntity != null) {
            return Endereco.builder()
                    .id(enderecoEntity.getId())
                    .rua(enderecoEntity.getRua())
                    .numero(enderecoEntity.getNumero())
                    .bairro(enderecoEntity.getBairro())
                    .cidade(enderecoEntity.getCidade())
                    .cep(enderecoEntity.getCep())
                    .complemento(enderecoEntity.getComplemento())
                    .dataCriacao(enderecoEntity.getDataCriacao())
                    .dataUltimaAlteracao(enderecoEntity.getDataUltimaAlteracao())
                .build();
        }
        return null;
    }

    public static ClienteJsonResponse convertToClienteJsonResponse(Cliente cliente){
        return new ClienteJsonResponse(
            cliente.getCpf(),
            cliente.getNome(),
            cliente.getDataNascimento(),
            cliente.getDataCriacao(),
            cliente.getDataUltimaAlteracao(),
            convertToEnderecoJsonResponse(cliente.getEndereco())
        );
    }

    public static EnderecoJsonResponse convertToEnderecoJsonResponse(Endereco endereco){
        EnderecoJsonResponse enderecoJsonResponse = new EnderecoJsonResponse(
            endereco.getId(),
            endereco.getRua(),
            endereco.getNumero(),
            endereco.getCep(),
            endereco.getComplemento(),
            endereco.getBairro(),
            endereco.getCidade(),
            endereco.getDataCriacao(),
            endereco.getDataUltimaAlteracao()
        );
        return enderecoJsonResponse;
    }

    public static ClienteEntity convertToClienteEntity(Cliente cliente, ClienteEntity clienteEntity) {
        var clienteEntityFinal = new ClienteEntity();
        if(clienteEntity != null && clienteEntity.getCpf() != null){
            clienteEntityFinal.setCpf(clienteEntity.getCpf());
            clienteEntityFinal.setDataCriacao(clienteEntity.getDataCriacao());
            clienteEntityFinal.setDataUltimaAlteracao(LocalDateTime.now());
        } else {
            clienteEntityFinal.setCpf(cliente.getCpf());
            clienteEntityFinal.setDataCriacao(LocalDateTime.now());
        }
        clienteEntityFinal.setNome(cliente.getNome());
        clienteEntityFinal.setDataNascimento(cliente.getDataNascimento());
        clienteEntityFinal.setEndereco(convertToEnderecoEntity(cliente.getEndereco(), clienteEntity != null ? clienteEntity.getEndereco(): null));
        return clienteEntityFinal;
    }

    public static EnderecoEntity convertToEnderecoEntity(Endereco endereco, EnderecoEntity enderecoEntity) {
        var enderecoEntityFinal = new EnderecoEntity();
        if(enderecoEntity != null && enderecoEntity.getId() != null) {
            enderecoEntityFinal.setId(enderecoEntity.getId());
            enderecoEntityFinal.setDataCriacao(enderecoEntity.getDataCriacao());
            enderecoEntityFinal.setDataUltimaAlteracao(LocalDateTime.now());
        } else {
            enderecoEntityFinal.setDataCriacao(LocalDateTime.now());
        }
        enderecoEntityFinal.setRua(endereco.getRua());
        enderecoEntityFinal.setNumero(endereco.getNumero());
        enderecoEntityFinal.setCep(endereco.getCep());
        enderecoEntityFinal.setComplemento(endereco.getComplemento());
        enderecoEntityFinal.setBairro(endereco.getBairro());
        enderecoEntityFinal.setCidade(endereco.getCidade());
        return enderecoEntityFinal;
    }

    public static void uuidValidator(UUID id) {
        if (!matches(REGEX_UUID, id.toString())) {
            throw new ResourceNotFoundException(ID_INVALIDO);
        }
    }
}
