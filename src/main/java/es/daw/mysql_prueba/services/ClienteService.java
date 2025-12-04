package es.daw.mysql_prueba.services;

import es.daw.mysql_prueba.entitys.Cliente;
import es.daw.mysql_prueba.exception.cliente.ClienteNotFoundException;
import es.daw.mysql_prueba.mappers.ClienteMapper;
import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import es.daw.mysql_prueba.models.clienteDTOs.ClientePedidoResponseDTO;
import es.daw.mysql_prueba.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    // -----------------INYECCIONES POR CONSTRUCTOR-----------------
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    private final PedidoService pedidoService;

    // -------------METODOS LLAMADOS POR EL CONTROLLER---------------
    public List<ClienteDTO> getAllClientes() {
        return clienteMapper.toDTOs(clienteRepository.findAll());
    }

    public ClientePedidoResponseDTO getPedidosByClienteId(Long idCliente) {
        this.findById(idCliente);

        return clienteMapper.toResponseDTO(pedidoService.findByClienteId(idCliente));
    }


    // ----------------------METODOS AUXILIARES----------------------
    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    public ClienteDTO toDto(Cliente cliente) {
        return clienteMapper.toDTO(cliente);
    }
}
