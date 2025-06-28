package br.com.fiap.ms.cliente.cliente.utils;

import br.com.fiap.ms.cliente.cliente.controller.json.ClienteJson;
import br.com.fiap.ms.cliente.cliente.domain.Cliente;
import br.com.fiap.ms.cliente.cliente.domain.Endereco;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.entity.ClienteEntity;
import br.com.fiap.ms.cliente.cliente.gateway.database.jpa.entity.EnderecoEntity;

public class ClienteUtils {

    private ClienteUtils() {}

    public static Cliente convertToCliente(ClienteEntity clienteEntity){
        return Cliente.builder()
                .cpf(clienteEntity.getCpf())
                .nome(clienteEntity.getNome())
                .dataNascimento(clienteEntity.getDataNascimento())
                .endereco(convertToEndereco(clienteEntity.getEndereco()))
                .dataCriacao(clienteEntity.getDataCriacao())
                .dataUltimaAlteracao(clienteEntity.getDataUltimaAlteracao())
            .build();
    }

    public static Endereco convertToEndereco(EnderecoEntity enderecoEntity) {
        return Endereco.builder()
                .id(enderecoEntity.getId())
                .rua(enderecoEntity.getRua())
                .numero(enderecoEntity.getNumero())
                .bairro(enderecoEntity.getBairro())
                .cidade(enderecoEntity.getCidade())
                .cep(enderecoEntity.getCep())
                .dataCriacao(enderecoEntity.getDataCriacao())
                .dataUltimaAlteracao(enderecoEntity.getDataUltimaAlteracao())
            .build();
    }

    public static ClienteJson convertToClienteJson(Cliente cliente){
        return new ClienteJson(
            cliente.getCpf(),
            cliente.getNome(),
            cliente.getDataNascimento(),
            cliente.getEndereco().getRua(),
            cliente.getEndereco().getNumero(),
            cliente.getEndereco().getComplemento(),
            cliente.getEndereco().getBairro(),
            cliente.getEndereco().getCidade(),
            cliente.getDataCriacao(),
            cliente.getDataUltimaAlteracao()
        );
    }

}
