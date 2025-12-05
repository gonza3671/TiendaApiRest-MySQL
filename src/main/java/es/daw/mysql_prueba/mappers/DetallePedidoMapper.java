package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedido;
import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedidoId;
import es.daw.mysql_prueba.models.productoDTOs.DetallePedidoDTO;
import es.daw.mysql_prueba.models.productoDTOs.ProductoYCantidadRequestDTO;
import es.daw.mysql_prueba.services.ProductoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DetallePedidoMapper {

    protected ProductoService productoService;

    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Mappings({
            @Mapping(source = "producto.nombre", target = "nombre"),
            @Mapping(source = "producto.precio", target = "precio"),
    })
    public abstract DetallePedidoDTO toDto(DetallePedido detallePedido);

    public abstract List<DetallePedidoDTO> toDtos(List<DetallePedido> detalles);

    @Mappings({
            @Mapping(target = "producto", expression = "java(productoService.findById(dto.getIdProducto()))"),
            @Mapping(target = "precioUnidad", expression = "java(productoService.findById(dto.getIdProducto()).getPrecio())"),
            @Mapping(target = "id", source = "dto")
    })
    public abstract DetallePedido toEntity(ProductoYCantidadRequestDTO dto);

    protected DetallePedidoId map(ProductoYCantidadRequestDTO dto) {
        DetallePedidoId id = new DetallePedidoId();
        id.setProductoId(dto.getIdProducto());
        return id;
    }

    public abstract List<DetallePedido> toEntitys(List<ProductoYCantidadRequestDTO> dtos);
}
