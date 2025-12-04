package es.daw.mysql_prueba.exception.pedido;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(Long id) {
        super("Pedido con id " + id + " no encontrado");
    }
}
