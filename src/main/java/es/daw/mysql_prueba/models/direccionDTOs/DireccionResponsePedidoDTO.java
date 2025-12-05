package es.daw.mysql_prueba.models.direccionDTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DireccionResponsePedidoDTO {
    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;
    private String provincia;
}
