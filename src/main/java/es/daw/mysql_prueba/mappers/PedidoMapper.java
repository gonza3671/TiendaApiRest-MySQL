package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.models.pedidoDTOs.PedidoDTO;
import es.daw.mysql_prueba.models.pedidoDTOs.PedidoRequestDTO;
import es.daw.mysql_prueba.models.pedidoDTOs.PedidoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {DetallePedidoMapper.class, ClienteMapper.class, DireccionMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoMapper {

    @Mapping(target = "productos", source = "detallePedidos")
    @Mapping(target = "fechaPedido", source = "fecha")
    @Mapping(target = "status", source = "estado")
    PedidoDTO toDto(Pedido entity);

    List<PedidoDTO> toDtos(List<Pedido> dtos);

    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "pedido", source = "entity")
    PedidoResponseDTO toDtoResponse(Pedido entity);

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "direccion", ignore = true)
    @Mapping(target = "estado", constant = "PENDIENTE")
    Pedido toEntity(PedidoRequestDTO dto);
}