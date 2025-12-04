package es.daw.mysql_prueba.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DireccionResponsePedidoDTO {
    private String pais;
    private String provincia;
    private String ciudad;
    private String codigoPostal;
    private String calle;
}
