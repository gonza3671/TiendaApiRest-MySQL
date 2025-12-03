package es.daw.mysql_prueba.entitys.pedido_producto;

import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
public class DetallePedidoId implements Serializable {

    private Long pedidoId;
    private Long productoId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedidoId that = (DetallePedidoId) o;
        return Objects.equals(pedidoId, that.pedidoId) &&
               Objects.equals(productoId, that.productoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, productoId);
    }
}
