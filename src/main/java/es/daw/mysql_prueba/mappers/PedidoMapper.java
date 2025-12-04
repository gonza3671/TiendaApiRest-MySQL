package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.enums.StatusPedido;
import es.daw.mysql_prueba.exception.cliente.ClienteNotFoundException;
import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoDTO;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoRequestDTO;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoResponseDTO;
import es.daw.mysql_prueba.repository.ClienteRepository;
import es.daw.mysql_prueba.repository.DireccionRepository;
import es.daw.mysql_prueba.services.ClienteService;
import es.daw.mysql_prueba.services.DetallePedidoService;
import es.daw.mysql_prueba.services.DireccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoMapper {

    // -------------- SERVICIOS --------------
    private final DetallePedidoService detallePedidoService;
    private final DireccionService direccionService;

    private final ClienteRepository clienteRepository;

    // ------------ DTO MAPPERS --------------
    public PedidoDTO toDto(Pedido entity) {

        return PedidoDTO
                .builder()
                .productos(detallePedidoService.toDtos(entity.getDetallePedidos().stream().toList()))
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
                        .build()
                )
                .direccion(direccionService.toDto(entity.getDireccion()))
                .pedido(this.toDto(entity))
                .build();
    }

    public List<PedidoDTO> toDtos(List<Pedido> dtos) {
        return dtos
                .stream()
                .map(this::toDto)
                .toList();
    }

    // ------------ ENTITY MAPPERS --------------
    public Pedido toEntity(PedidoRequestDTO dto) {
        Pedido entity = new Pedido();
        entity.setCliente(clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ClienteNotFoundException(dto.getIdCliente())));
        entity.setDireccion(direccionService.findById(dto.getIdDireccion()));
        entity.setEstado(StatusPedido.PENDIENTE);

        return entity;
    }
}
