package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.Cliente;
import es.daw.mysql_prueba.entitys.Direccion;
import es.daw.mysql_prueba.models.direccionDTOs.DireccionDTO;
import es.daw.mysql_prueba.models.direccionDTOs.DireccionResponsePedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Context;

@Mapper(componentModel = "spring")
public interface DireccionMapper {

    DireccionResponsePedidoDTO toDto(Direccion entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", source = "cliente")
    Direccion toEntity(DireccionDTO dto, Cliente cliente);
}
