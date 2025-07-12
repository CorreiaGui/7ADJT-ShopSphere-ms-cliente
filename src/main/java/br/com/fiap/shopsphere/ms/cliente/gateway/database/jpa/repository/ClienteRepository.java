package br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.repository;

import br.com.fiap.shopsphere.ms.cliente.gateway.database.jpa.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
    public Optional<ClienteEntity> findByCpf(String cpf);
    public int deleteByCpf(String cpf);
}
