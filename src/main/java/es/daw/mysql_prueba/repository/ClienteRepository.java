package es.daw.mysql_prueba.repository;

import es.daw.mysql_prueba.entitys.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Optional<Cliente> findById(Long id);
}
