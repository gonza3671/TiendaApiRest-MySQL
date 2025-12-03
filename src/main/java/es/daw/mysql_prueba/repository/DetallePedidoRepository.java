package es.daw.mysql_prueba.repository;

import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
}
