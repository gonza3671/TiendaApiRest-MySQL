package es.daw.mysql_prueba.models.productoDTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoYCantidadRequestDTO {
    private Long idProducto;
    private Integer cantidad;
}
