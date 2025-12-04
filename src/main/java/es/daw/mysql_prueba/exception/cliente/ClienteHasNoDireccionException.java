package es.daw.mysql_prueba.exception.cliente;

public class ClienteHasNoDireccionException extends RuntimeException {
    public ClienteHasNoDireccionException(Long idCliente) {
        super("El cliente con id " + idCliente + " no tiene una dirección asociada.");
    }
}
