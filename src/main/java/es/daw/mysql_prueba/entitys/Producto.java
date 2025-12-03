package es.daw.mysql_prueba.entitys;

import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedido;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "productos")
@Getter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;

    @Column(name = "nombre_producto" ,length = 100, nullable = false)
    private String nombre;

    private BigDecimal precio;

    private Integer stock;

    @OneToMany(mappedBy = "producto")
    private Set<DetallePedido> detallePedidos;
}
