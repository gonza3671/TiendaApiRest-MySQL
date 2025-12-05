package es.daw.mysql_prueba.models.pedidoDTOs;

import es.daw.mysql_prueba.models.direccionDTOs.DireccionResponsePedidoDTO;
import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoResponseDTO {
    private ClienteDTO cliente;
    private DireccionResponsePedidoDTO direccion;
    private PedidoDTO pedido;
}
