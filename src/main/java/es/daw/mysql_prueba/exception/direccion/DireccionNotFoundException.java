package es.daw.mysql_prueba.exception.direccion;

public class DireccionNotFoundException extends RuntimeException {
    public DireccionNotFoundException(Long id) {
        super("Direccion con id " + id + " no encontrado");
    }
}
