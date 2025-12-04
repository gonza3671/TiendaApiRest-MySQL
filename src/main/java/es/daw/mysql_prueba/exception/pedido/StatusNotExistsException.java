package es.daw.mysql_prueba.exception.pedido;

public class StatusNotExistsException extends RuntimeException {
    public StatusNotExistsException(String statusInexistente) {
        super("El estado '" + statusInexistente + "' no existe.");
    }
}
