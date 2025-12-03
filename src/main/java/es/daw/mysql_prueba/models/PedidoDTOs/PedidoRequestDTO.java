package es.daw.mysql_prueba.models.PedidoDTOs;

import es.daw.mysql_prueba.models.ProductoYCantidadRequestDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PedidoRequestDTO {
    private Long idCliente;
    private List<ProductoYCantidadRequestDTO> productos;
}
