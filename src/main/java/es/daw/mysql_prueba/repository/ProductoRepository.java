package es.daw.mysql_prueba.repository;

import es.daw.mysql_prueba.entitys.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    public Optional<Producto> findById(Long idProducto);
}
