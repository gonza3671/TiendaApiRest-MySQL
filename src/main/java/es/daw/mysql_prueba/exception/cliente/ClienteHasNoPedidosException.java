package es.daw.mysql_prueba.exception.cliente;

public class ClienteHasNoPedidosException extends RuntimeException {
    public ClienteHasNoPedidosException(Long idCliente) {
        super("El cliente con id " + idCliente + " no tiene pedidos.");
    }
}
