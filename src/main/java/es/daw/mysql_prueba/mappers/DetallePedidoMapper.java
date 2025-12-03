package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedido;
import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedidoId;
import es.daw.mysql_prueba.models.DetallePedidoDTO;
import es.daw.mysql_prueba.models.ProductoYCantidadRequestDTO;
import es.daw.mysql_prueba.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DetallePedidoMapper {

    private final ProductoService productoService;

    public DetallePedidoDTO toDto(DetallePedido pedido) {
        return DetallePedidoDTO
                .builder()
                .nombre(pedido.getProducto().getNombre())
                .cantidad(pedido.getCantidad())
                .precio(pedido.getProducto().getPrecio())
                .build();
    }

    public List<DetallePedidoDTO> toDtos(List<DetallePedido> pedido) {
        return pedido
                .stream()
                .map(this::toDto)
                .toList();
    }

    public DetallePedido toEntity(ProductoYCantidadRequestDTO dto) {
        DetallePedido entity = new DetallePedido();

        DetallePedidoId id = new DetallePedidoId();
        id.setProductoId(dto.getIdProducto());

        entity.setId(id);
        entity.setCantidad(dto.getCantidad());
        entity.setProducto(productoService.findById(dto.getIdProducto()));
        entity.setPrecioUnidad(productoService.findById(dto.getIdProducto()).getPrecio());

        return entity;
    }

    public List<DetallePedido> toEntitys(List<ProductoYCantidadRequestDTO> dtos) {
        return dtos
                .stream()
                .map(this::toEntity)
                .toList();
    }
}
