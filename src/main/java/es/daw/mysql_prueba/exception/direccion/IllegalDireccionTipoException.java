package es.daw.mysql_prueba.exception.direccion;

public class IllegalDireccionTipoException extends RuntimeException {
    public IllegalDireccionTipoException(String tipoDireccion) {
        super("El tipo de dirección '" + tipoDireccion + "' no es válido para este campo.");
    }
}
