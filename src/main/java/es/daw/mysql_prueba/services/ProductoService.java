package es.daw.mysql_prueba.services;

import es.daw.mysql_prueba.entitys.Producto;
import es.daw.mysql_prueba.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public Producto findById(Long idProducto) {
        return productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + idProducto)); // Excepción personalizada pendiente
    }
}
