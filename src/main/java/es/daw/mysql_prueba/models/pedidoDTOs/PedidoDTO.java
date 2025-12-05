package es.daw.mysql_prueba.models.pedidoDTOs;

import es.daw.mysql_prueba.enums.StatusPedido;
import es.daw.mysql_prueba.models.direccionDTOs.DireccionResponsePedidoDTO;
import es.daw.mysql_prueba.models.productoDTOs.DetallePedidoDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PedidoDTO {
    private DireccionResponsePedidoDTO direccion;
    private LocalDateTime fechaPedido;
    private StatusPedido status;
    private List<DetallePedidoDTO> productos;
}
