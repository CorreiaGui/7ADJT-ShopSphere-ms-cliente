package br.com.fiap.ms.cliente.cliente.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private UUID id;

    private String rua;

    private String numero;

    private String cep;

    private String complemento;

    private String bairro;

    private String cidade;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataUltimaAlteracao;

}
