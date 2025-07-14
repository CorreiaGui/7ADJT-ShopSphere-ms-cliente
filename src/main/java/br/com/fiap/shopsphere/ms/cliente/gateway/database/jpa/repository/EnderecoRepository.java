package br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.repository;

import br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, UUID> {
}
