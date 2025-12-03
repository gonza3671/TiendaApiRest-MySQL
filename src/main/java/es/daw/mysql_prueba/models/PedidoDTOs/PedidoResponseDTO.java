package es.daw.mysql_prueba.models.PedidoDTOs;

import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoResponseDTO {
    private ClienteDTO cliente;
    private PedidoDTO pedido;
}
