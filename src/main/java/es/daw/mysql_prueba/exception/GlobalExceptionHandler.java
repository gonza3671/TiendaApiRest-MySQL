package es.daw.mysql_prueba.exception;

import es.daw.mysql_prueba.exception.cliente.ClienteHasNoDireccionException;
import es.daw.mysql_prueba.exception.cliente.ClienteHasNoPedidosException;
import es.daw.mysql_prueba.exception.cliente.ClienteNotFoundException;
import es.daw.mysql_prueba.exception.direccion.AddressMissMatchException;
import es.daw.mysql_prueba.exception.direccion.DireccionNotFoundException;
import es.daw.mysql_prueba.exception.direccion.IllegalDireccionTipoException;
import es.daw.mysql_prueba.exception.pedido.PedidoNotFoundException;
import es.daw.mysql_prueba.exception.pedido.StatusNotExistsException;
import es.daw.mysql_prueba.exception.pedido.StatusUnchangedException;
import es.daw.mysql_prueba.exception.producto.InsufficientStockException;
import es.daw.mysql_prueba.exception.producto.ProductoNotFoundException;
import es.daw.mysql_prueba.models.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ProductoNotFoundException.class,
            ClienteNotFoundException.class,
            PedidoNotFoundException.class,
            StatusUnchangedException.class,
            StatusNotExistsException.class,
            InsufficientStockException.class,
            AddressMissMatchException.class,
            IllegalDireccionTipoException.class,
            DireccionNotFoundException.class,
            ClienteHasNoDireccionException.class,
            ClienteHasNoPedidosException.class
    })
    public ResponseEntity<ErrorDTO> handleAppExceptions(RuntimeException ex) {
        ErrorDTO error = ErrorDTO.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorDTO.builder()
                        .message("Error generico: " + ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
