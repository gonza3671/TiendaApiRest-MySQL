package es.daw.mysql_prueba.models.PedidoDTOs;

import es.daw.mysql_prueba.enums.StatusPedido;
import es.daw.mysql_prueba.models.DetallePedidoDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PedidoDTO {
    private LocalDateTime fechaPedido;
    private StatusPedido status;
    private List<DetallePedidoDTO> productos;
}
