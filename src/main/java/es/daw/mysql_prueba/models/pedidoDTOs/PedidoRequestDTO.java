package es.daw.mysql_prueba.models.pedidoDTOs;

import es.daw.mysql_prueba.models.productoDTOs.ProductoYCantidadRequestDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PedidoRequestDTO {
    private Long idCliente;
    private Long idDireccion;
    private List<ProductoYCantidadRequestDTO> productos;
}
