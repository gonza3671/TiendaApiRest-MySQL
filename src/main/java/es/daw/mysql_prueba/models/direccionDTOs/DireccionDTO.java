package es.daw.mysql_prueba.models.direccionDTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DireccionDTO {
    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;
    private String provincia;
    private String direccionTipo;
}
