package es.daw.mysql_prueba.repository;

import es.daw.mysql_prueba.entitys.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
