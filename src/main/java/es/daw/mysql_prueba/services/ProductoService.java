package es.daw.mysql_prueba.services;

import es.daw.mysql_prueba.entitys.Producto;
import es.daw.mysql_prueba.exception.producto.ProductoNotFoundException;
import es.daw.mysql_prueba.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService {

    // -----------------INYECCIONES POR CONSTRUCTOR-----------------
    private final ProductoRepository productoRepository;

    // ----------------------METODOS AUXILIARES----------------------
    public Producto findById(Long idProducto) {
        return productoRepository.findById(idProducto)
                .orElseThrow(() -> new ProductoNotFoundException(idProducto));
    }

    public void saveProducto(Producto producto) {
        productoRepository.save(producto);
    }
}
