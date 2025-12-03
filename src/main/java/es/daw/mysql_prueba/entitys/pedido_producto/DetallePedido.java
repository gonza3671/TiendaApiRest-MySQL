package es.daw.mysql_prueba.entitys.pedido_producto;

import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.entitys.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
@Getter
@Setter
public class DetallePedido {

    @EmbeddedId
    private DetallePedidoId id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Integer cantidad;

    @Column(name = "precio_unidad")
    private BigDecimal precioUnidad;
}
