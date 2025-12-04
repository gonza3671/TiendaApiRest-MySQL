package es.daw.mysql_prueba.services;

import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedido;
import es.daw.mysql_prueba.mappers.DetallePedidoMapper;
import es.daw.mysql_prueba.models.productoDTOs.DetallePedidoDTO;
import es.daw.mysql_prueba.models.productoDTOs.ProductoYCantidadRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallePedidoService {

    // -----------------INYECCIONES POR CONSTRUCTOR-----------------
    private final DetallePedidoMapper detallePedidoMapper;



    // ----------------------METODOS AUXILIARES----------------------
    public List<DetallePedidoDTO> toDtos(List<DetallePedido> pedido) {
        return detallePedidoMapper.toDtos(pedido);
    }

    public List<DetallePedido> toEntitys(List<ProductoYCantidadRequestDTO> dtos) {
        return detallePedidoMapper.toEntitys(dtos);
    }
}
