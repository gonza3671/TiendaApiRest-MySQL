package es.daw.mysql_prueba.services;

import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedido;
import es.daw.mysql_prueba.enums.StatusPedido;
import es.daw.mysql_prueba.exception.PedidoNotFoundException;
import es.daw.mysql_prueba.exception.StatusUnchangedException;
import es.daw.mysql_prueba.mappers.DetallePedidoMapper;
import es.daw.mysql_prueba.mappers.PedidoMapper;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoDTO;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoRequestDTO;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoResponseDTO;
import es.daw.mysql_prueba.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final DetallePedidoMapper detallePedidoMapper;

    @Transactional
    public PedidoResponseDTO createPedido(PedidoRequestDTO pedido) {
        clienteService.getClienteById(pedido.getIdCliente());

        pedido.getProductos()
                .forEach(p ->
                    productoService.findById(p.getIdProducto())
                ); // Verifica que todos los productos existen

        Pedido pedidoNuevo = pedidoMapper.toEntity(pedido);

        pedidoNuevo.setDetallePedidos(new HashSet<>());

        List<DetallePedido> detalles = detallePedidoMapper.toEntitys(pedido.getProductos());


        detalles.forEach(d -> {
                    d.setPedido(pedidoNuevo);
                    pedidoNuevo.getDetallePedidos().add(d);
                });

        Pedido pedidoSaved = pedidoRepository.save(pedidoNuevo);


        return pedidoMapper.toDtoResponse(pedidoSaved);
    }

    public PedidoResponseDTO editarStatus(Long idPedido, String nuevoStatus) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow( () -> new PedidoNotFoundException(idPedido));

        if (nuevoStatus.equalsIgnoreCase(pedido.getEstado().name())) {
            throw new StatusUnchangedException(nuevoStatus);
        }

        pedido.setEstado(StatusPedido.valueOf(nuevoStatus.toUpperCase()));

        Pedido pedidoUpdated = pedidoRepository.save(pedido);

        return pedidoMapper.toDtoResponse(pedidoUpdated);
    }
}
