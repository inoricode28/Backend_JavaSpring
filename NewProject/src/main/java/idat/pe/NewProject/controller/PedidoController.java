package idat.pe.NewProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.PedidoDtoRequest;
import idat.pe.NewProject.dto.PedidoDtoResponse;
import idat.pe.NewProject.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired private PedidoService service;

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoDtoResponse>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.listarPorCliente(clienteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDtoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<PedidoDtoResponse> crear(@PathVariable Long clienteId, @RequestBody PedidoDtoRequest request) {
        return ResponseEntity.ok(service.crear(clienteId, request));
    }

    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PedidoDtoResponse> actualizarEstado(@PathVariable Long id, @RequestParam Integer estado) {
        return ResponseEntity.ok(service.actualizarEstado(id, estado));
    }
}
