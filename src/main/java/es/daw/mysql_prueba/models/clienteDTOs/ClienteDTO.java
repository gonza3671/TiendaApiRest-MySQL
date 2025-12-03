package es.daw.mysql_prueba.models.clienteDTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {
    private String nombre;
    private String apellido;
    private String email;
}
