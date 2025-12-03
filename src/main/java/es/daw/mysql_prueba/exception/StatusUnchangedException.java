package es.daw.mysql_prueba.exception;

public class StatusUnchangedException extends RuntimeException {
    public StatusUnchangedException(String status) {
        super("El status nuevo no puede ser igual al anterior: status = " + status);
    }
}
