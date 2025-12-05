package es.daw.mysql_prueba.controllers;

import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import es.daw.mysql_prueba.models.ClientePedidoResponseDTO;
import es.daw.mysql_prueba.models.direccionDTOs.DireccionDTO;
import es.daw.mysql_prueba.models.direccionDTOs.DireccionResponsePedidoDTO;
import es.daw.mysql_prueba.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    // ----------------------SERVICES----------------------
    private final ClienteService clienteService;


    // ----------------------ENDPOINTS----------------------
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @GetMapping("/{idCliente}/pedidos" )
    public ResponseEntity<ClientePedidoResponseDTO> getPedidosByClienteId(
            @PathVariable Long idCliente) {
        return ResponseEntity.ok(clienteService.getPedidosByClienteId(idCliente));
    }

    @PostMapping("/{idCliente}/create/direccion")
    public ResponseEntity<DireccionResponsePedidoDTO> createDireccion(
            @RequestBody DireccionDTO direccionDTO,
            @PathVariable Long idCliente) {
        return ResponseEntity.ok(clienteService.createDireccion(direccionDTO, idCliente));
    }


}
