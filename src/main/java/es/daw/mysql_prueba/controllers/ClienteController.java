package es.daw.mysql_prueba.controllers;

import es.daw.mysql_prueba.models.clienteDTOs.ClienteDTO;
import es.daw.mysql_prueba.models.clienteDTOs.ClientePedidoResponseDTO;
import es.daw.mysql_prueba.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @GetMapping("/{idCliente}/pedidos" )
    public ResponseEntity<ClientePedidoResponseDTO> getPedidosByClienteId(@PathVariable Long idCliente) {
        return ResponseEntity.ok(clienteService.getPedidosByClienteId(idCliente));
    }
}
