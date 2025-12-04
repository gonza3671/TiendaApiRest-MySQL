package es.daw.mysql_prueba.models.PedidoDTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoRequestStatusDTO {
    private Long idPedido;
    private String status;
}
