package es.daw.mysql_prueba.models.clienteDTOs;

import es.daw.mysql_prueba.models.PedidoDTOs.PedidoDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientePedidoResponseDTO {
    private ClienteDTO cliente;
    private List<PedidoDTO> pedidos;
}
