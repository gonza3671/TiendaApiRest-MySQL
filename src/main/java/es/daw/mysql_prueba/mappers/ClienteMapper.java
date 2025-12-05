package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.Cliente;
import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDTO(Cliente cliente);

    List<ClienteDTO> toDTOs(List<Cliente> clientes);
}
