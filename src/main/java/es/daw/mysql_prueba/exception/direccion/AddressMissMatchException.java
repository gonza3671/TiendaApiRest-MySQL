package es.daw.mysql_prueba.exception.direccion;

public class AddressMissMatchException extends RuntimeException {
    public AddressMissMatchException(Long idCliente, Long idDireccion) {
        super("La dirección con id " + idDireccion + " no pertenece al cliente con id " + idCliente);
    }
}
