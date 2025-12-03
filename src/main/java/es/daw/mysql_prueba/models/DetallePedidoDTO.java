package es.daw.mysql_prueba.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DetallePedidoDTO {
    private String nombre;
    private Integer cantidad;
    private BigDecimal precio;
}
