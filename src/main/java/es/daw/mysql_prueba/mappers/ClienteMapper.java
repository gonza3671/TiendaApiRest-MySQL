package es.daw.mysql_prueba.mappers;

import es.daw.mysql_prueba.entitys.Cliente;
import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import es.daw.mysql_prueba.models.clienteDTOs.ClientePedidoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClienteMapper {

    private final PedidoMapper pedidoMapper;

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

    public Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setEmail(clienteDTO.getEmail());
        return cliente;
    }

    public List<ClienteDTO> toDTOs(List<Cliente> clientes) {
        return clientes
                .stream()
                .map(this::toDTO)
                .toList();
    }
}
