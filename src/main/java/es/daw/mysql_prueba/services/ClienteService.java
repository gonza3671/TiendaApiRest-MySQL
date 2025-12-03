package es.daw.mysql_prueba.services;

import es.daw.mysql_prueba.mappers.ClienteMapper;
import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import es.daw.mysql_prueba.models.clienteDTOs.ClientePedidoResponseDTO;
import es.daw.mysql_prueba.repository.ClienteRepository;
import es.daw.mysql_prueba.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    private final PedidoRepository pedidoRepository;

    public List<ClienteDTO> getAllClientes() {
        return clienteMapper.toDTOs(clienteRepository.findAll());
    }

    public ClientePedidoResponseDTO getPedidosByClienteId(Long idCliente) {
        this.getClienteById(idCliente);

        return clienteMapper.toResponseDTO(pedidoRepository.findByClienteId(idCliente));
    }

    public void getClienteById(Long idCliente) {
        clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}
