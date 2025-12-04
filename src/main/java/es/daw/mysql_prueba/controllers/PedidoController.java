package es.daw.mysql_prueba.controllers;

import es.daw.mysql_prueba.models.PedidoDTOs.PedidoRequestDTO;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoRequestStatusDTO;
import es.daw.mysql_prueba.models.PedidoDTOs.PedidoResponseDTO;
import es.daw.mysql_prueba.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("/create")
    public ResponseEntity<PedidoResponseDTO> createPedido(@RequestBody PedidoRequestDTO pedido) {
        return ResponseEntity.ok(pedidoService.createPedido(pedido));
    }

    @PutMapping("/edit/status")
    public ResponseEntity<PedidoResponseDTO> editarStatus(
            @RequestBody PedidoRequestStatusDTO request) {
        return ResponseEntity.ok(pedidoService.editarStatus(request));
    }
}
