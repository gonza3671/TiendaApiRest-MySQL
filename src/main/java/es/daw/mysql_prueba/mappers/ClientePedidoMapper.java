package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.Cliente;
import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.models.ClientePedidoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = {ClienteMapper.class, DireccionMapper.class, PedidoMapper.class})
public interface ClientePedidoMapper {

    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "pedidos", source = "pedidos")
    ClientePedidoResponseDTO toResponseDTO( List<Pedido> pedidos, Cliente cliente);
}