package es.daw.mysql_prueba.services;

import es.daw.mysql_prueba.entitys.Cliente;
import es.daw.mysql_prueba.entitys.Direccion;
import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.exception.cliente.ClienteNotFoundException;
import es.daw.mysql_prueba.mappers.ClienteMapper;
import es.daw.mysql_prueba.mappers.ClientePedidoMapper;
import es.daw.mysql_prueba.mappers.DireccionMapper;
import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import es.daw.mysql_prueba.models.ClientePedidoResponseDTO;
import es.daw.mysql_prueba.models.direccionDTOs.DireccionDTO;
import es.daw.mysql_prueba.models.direccionDTOs.DireccionResponsePedidoDTO;
import es.daw.mysql_prueba.repository.ClienteRepository;
import es.daw.mysql_prueba.repository.DireccionRepository;
import es.daw.mysql_prueba.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClienteService {

    // -----------------INYECCIONES POR CONSTRUCTOR-----------------
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final ClientePedidoMapper clientePedidoMapper;

    private final DireccionMapper direccionMapper;
    private final DireccionRepository direccionRepository;
    private final PedidoService pedidoService;

    // -------------METODOS LLAMADOS POR ENDPOINTS---------------
    public List<ClienteDTO> getAllClientes() {
        return clienteMapper.toDTOs(clienteRepository.findAll());
    }

    public ClientePedidoResponseDTO getPedidosByClienteId(Long idCliente) {
        findById(idCliente);

        List<Pedido> pedidos = pedidoService.findByClienteId(idCliente);

        if (pedidos.isEmpty()) {
            return ClientePedidoResponseDTO.builder()
                            .cliente(clienteMapper.toDTO(this.findById(idCliente)))
                            .pedidos(List.of())
                            .build();
        }

        return clientePedidoMapper.toResponseDTO(pedidos, pedidos.get(0).getCliente());
    }

    public DireccionResponsePedidoDTO createDireccion(DireccionDTO direccionDTO, Long idCliente) {

        Direccion direccion = direccionMapper.toEntity(direccionDTO, findById(idCliente));

        direccionRepository.save(direccion);

        return direccionMapper.toDto(direccion);
    }

    // ----------------------METODOS AUXILIARES----------------------
    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }
}
