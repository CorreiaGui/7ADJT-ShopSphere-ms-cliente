package br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "cliente", schema = "ms_cliente")
public class ClienteEntity {

    @Id
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cliente_endereco"))
    private EnderecoEntity endereco;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;
}
