package es.daw.mysql_prueba.services;

import es.daw.mysql_prueba.entitys.Direccion;
import es.daw.mysql_prueba.exception.direccion.DireccionNotFoundException;
import es.daw.mysql_prueba.mappers.DireccionMapper;
import es.daw.mysql_prueba.models.direccionDTOs.DireccionResponsePedidoDTO;
import es.daw.mysql_prueba.repository.DireccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DireccionService {

    // -----------------INYECCIONES POR CONSTRUCTOR-----------------
    private final DireccionRepository direccionRepository;
    private final DireccionMapper direccionMapper;

    // ----------------------METODOS AUXILIARES----------------------
    public Direccion findById(Long id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new DireccionNotFoundException(id));
    }

    public DireccionResponsePedidoDTO toDto(Direccion entity) {
        return direccionMapper.toDto(entity);
    }
}
