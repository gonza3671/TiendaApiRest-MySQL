package es.daw.mysql_prueba.controllers;

import es.daw.mysql_prueba.models.pedidoDTOs.PedidoRequestDTO;
import es.daw.mysql_prueba.models.pedidoDTOs.PedidoRequestStatusDTO;
import es.daw.mysql_prueba.models.pedidoDTOs.PedidoResponseDTO;
import es.daw.mysql_prueba.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    // ----------------------SERVICES----------------------
    private final PedidoService pedidoService;


    // ----------------------ENDPOINTS----------------------
    @PostMapping("/create")
    public ResponseEntity<PedidoResponseDTO> createPedido( // añadir mas tarde api para forma de pago
            @RequestBody PedidoRequestDTO pedido) {
        return ResponseEntity.ok(pedidoService.createPedido(pedido));
    }

    @PutMapping("/edit/status")
    public ResponseEntity<PedidoResponseDTO> editarStatus(
            @RequestBody PedidoRequestStatusDTO request) {
        return ResponseEntity.ok(pedidoService.editarStatus(request));
    }
}
