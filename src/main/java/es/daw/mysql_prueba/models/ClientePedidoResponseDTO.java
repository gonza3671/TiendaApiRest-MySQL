package es.daw.mysql_prueba.models;

import es.daw.mysql_prueba.models.pedidoDTOs.PedidoDTO;
import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientePedidoResponseDTO {
    private ClienteDTO cliente;
    private List<PedidoDTO> pedidos;
}
