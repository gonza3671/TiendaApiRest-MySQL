package es.daw.mysql_prueba.exception.producto;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long idProducto) {
        super("No hay suficiente stock para el producto con ID: " + idProducto);
    }
}
