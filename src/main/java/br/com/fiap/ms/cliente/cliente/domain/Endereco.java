package br.com.fiap.ms.cliente.cliente.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    private UUID id;

    private String rua;

    private Integer numero;

    private String cep;

    private String complemento;

    private String bairro;

    private String cidade;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataUltimaAlteracao;

}
