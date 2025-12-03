package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.enums.StatusPedido;
import es.daw.mysql_prueba.exception.ClienteNotFoundException;
import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoDTO;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoRequestDTO;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoResponseDTO;
import es.daw.mysql_prueba.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoMapper {

    private final DetallePedidoMapper detallePedidoMapper;
    private final ClienteRepository clienteRepository;

    public PedidoDTO toDto(Pedido entity) {

        return PedidoDTO
                .builder()
                .productos(detallePedidoMapper.toDtos(entity.getDetallePedidos().stream().toList()))
                .fechaPedido(entity.getFecha())
                .status(entity.getEstado())
                .build();
    }

    public PedidoResponseDTO toDtoResponse(Pedido entity) {

        return PedidoResponseDTO
                .builder()
                .cliente(ClienteDTO
                        .builder()
                        .nombre(entity.getCliente().getNombre())
                        .apellido(entity.getCliente().getApellido())
                        .email(entity.getCliente().getEmail())
                        .build())
                .pedido(this.toDto(entity))
                .build();
    }

    public List<PedidoDTO> toDtos(List<Pedido> dtos) {
        return dtos
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Pedido toEntity(PedidoRequestDTO dto) {
        Pedido entity = new Pedido();
        entity.setCliente(clienteRepository.findById(dto.getIdCliente())
        .orElseThrow(() -> new ClienteNotFoundException(dto.getIdCliente())));

        entity.setEstado(StatusPedido.PENDIENTE);

        return entity;
    }
}
