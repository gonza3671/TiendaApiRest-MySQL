package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.Direccion;
import es.daw.mysql_prueba.models.DireccionResponsePedidoDTO;
import org.springframework.stereotype.Component;

@Component
public class DireccionMapper {

    // ------------ DTO MAPPERS --------------
    public DireccionResponsePedidoDTO toDto(Direccion entity) {
        return DireccionResponsePedidoDTO
                .builder()
                .calle(entity.getCalle())
                .ciudad(entity.getCiudad())
                .codigoPostal(entity.getCodigoPostal())
                .pais(entity.getPais())
                .build();
    }
}
