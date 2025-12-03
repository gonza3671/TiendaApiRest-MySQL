package es.daw.mysql_prueba.repository;

import es.daw.mysql_prueba.entitys.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    public List<Pedido> findByClienteId(Long id);
}
