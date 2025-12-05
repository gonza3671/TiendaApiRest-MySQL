package es.daw.mysql_prueba.services;

import es.daw.mysql_prueba.entitys.Pedido;
import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedido;
import es.daw.mysql_prueba.enums.DireccionTipo;
import es.daw.mysql_prueba.enums.StatusPedido;
import es.daw.mysql_prueba.exception.cliente.ClienteHasNoDireccionException;
import es.daw.mysql_prueba.exception.cliente.ClienteNotFoundException;
import es.daw.mysql_prueba.exception.direccion.AddressMissMatchException;
import es.daw.mysql_prueba.exception.direccion.IllegalDireccionTipoException;
import es.daw.mysql_prueba.exception.producto.InsufficientStockException;
import es.daw.mysql_prueba.exception.pedido.PedidoNotFoundException;
import es.daw.mysql_prueba.exception.pedido.StatusNotExistsException;
import es.daw.mysql_prueba.exception.pedido.StatusUnchangedException;
import es.daw.mysql_prueba.mappers.PedidoMapper;
import es.daw.mysql_prueba.models.pedidoDTOs.PedidoRequestDTO;
import es.daw.mysql_prueba.models.pedidoDTOs.PedidoRequestStatusDTO;
import es.daw.mysql_prueba.models.pedidoDTOs.PedidoResponseDTO;
import es.daw.mysql_prueba.repository.ClienteRepository;
import es.daw.mysql_prueba.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    // -----------------INYECCIONES POR CONSTRUCTOR-----------------
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    private final ProductoService productoService;
    private final DetallePedidoService detallePedidoService;
    private final ClienteRepository clienteRepository;

    // ---------------METODOS LLAMADOS POR ENDPOINTS-----------------
    @Transactional
    public PedidoResponseDTO createPedido(PedidoRequestDTO pedido) {
        // comprobaciones de existencia
        if (clienteRepository.findById(pedido.getIdCliente())
                .orElseThrow(() -> new ClienteNotFoundException(pedido.getIdCliente())).getDirecciones().isEmpty()) {
            throw new ClienteHasNoDireccionException(pedido.getIdCliente());
        }
        pedido.getProductos().forEach(p -> productoService.findById(p.getIdProducto()));

        // mapeo de DTO a entidad
        Pedido pedidoNuevo = pedidoMapper.toEntity(pedido);

        // comprobaciones de dirección
        if (!pedidoNuevo.getCliente().getId().equals(pedidoNuevo.getDireccion().getCliente().getId())) {
            throw new AddressMissMatchException(pedidoNuevo.getCliente().getId(), pedidoNuevo.getDireccion().getId());
        }
        if (pedidoNuevo.getDireccion().getDireccionTipo().equals(DireccionTipo.FACTURACION)) {
            throw new IllegalDireccionTipoException(DireccionTipo.FACTURACION.name());
        }

        // inicializar detalles y comprobar stock
        pedidoNuevo.setDetallePedidos(new HashSet<>());
        List<DetallePedido> detalles = detallePedidoService.toEntitys(pedido.getProductos());
        detalles.forEach(d -> {
                    d.setPedido(pedidoNuevo);
                    pedidoNuevo.getDetallePedidos().add(d);
                    if (d.getProducto().getStock() < d.getCantidad()) {
                        throw new InsufficientStockException(d.getProducto().getId());
                    }
                });

        // guardar pedido
        Pedido pedidoSaved = pedidoRepository.save(pedidoNuevo);

        // actualizar stock productos
        detalles.forEach(d -> {
            d.getProducto().setStock(d.getProducto().getStock() - d.getCantidad());
            productoService.saveProducto(d.getProducto());
        });

        return pedidoMapper.toDtoResponse(pedidoSaved);
    }

    public PedidoResponseDTO editarStatus(PedidoRequestStatusDTO request) {
        // mapear DTO a entidad
        Pedido pedido = this.findById(request.getIdPedido());

        // comprobaciones de status
        if (request.getStatus().equalsIgnoreCase(pedido.getEstado().name())) {
            throw new StatusUnchangedException(request.getStatus());
        }
        if (!StatusPedido.exists(request.getStatus())) {
            throw new StatusNotExistsException(request.getStatus());
        }
        if (pedido.getEstado().equals(StatusPedido.CANCELADO) ||
                pedido.getEstado().equals(StatusPedido.ENTREGADO)) {
            throw new RuntimeException(request.getStatus()); // añadir excepcion personalizada
        }

        // actualizar y guardar
        pedido.setEstado(StatusPedido.valueOf(request.getStatus().toUpperCase()));
        Pedido pedidoUpdated = pedidoRepository.save(pedido);

        return pedidoMapper.toDtoResponse(pedidoUpdated);
    }

    // ---------------------METODOS AUXILIARES----------------------
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow( () -> new PedidoNotFoundException(id) );
    }

    public List<Pedido> findByClienteId(Long idCliente) {
        return pedidoRepository.findByClienteId(idCliente);
    }
}
