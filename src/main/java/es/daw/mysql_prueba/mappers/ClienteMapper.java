package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.Cliente;
import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import es.daw.mysql_prueba.models.clienteDTOs.ClientePedidoResponseDTO;
import es.daw.mysql_prueba.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClienteMapper {

    // -------------- SERVICIOS --------------
    private final PedidoMapper pedidoMapper;

    // ------------ DTO MAPPERS --------------
    public ClienteDTO toDTO(Cliente cliente) {
        return ClienteDTO
                .builder()
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .email(cliente.getEmail())
                .build();
    }

    public ClientePedidoResponseDTO toResponseDTO(List<Pedido> pedidos) {
        return ClientePedidoResponseDTO
                .builder()
                .cliente(this.toDTO(pedidos.get(0).getCliente()))
                .pedidos(pedidoMapper.toDtos(pedidos))
                .build();
    }

    public List<ClienteDTO> toDTOs(List<Cliente> clientes) {
        return clientes
                .stream()
                .map(this::toDTO)
                .toList();
    }
}
