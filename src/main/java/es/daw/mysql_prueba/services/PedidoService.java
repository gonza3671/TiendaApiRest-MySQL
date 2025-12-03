package es.daw.mysql_prueba.services;

import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedido;
import es.daw.mysql_prueba.mappers.DetallePedidoMapper;
import es.daw.mysql_prueba.mappers.PedidoMapper;
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
}
