package idat.pe.NewProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.VentaDtoResponse;
import idat.pe.NewProject.service.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired private VentaService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<VentaDtoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VentaDtoResponse>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.listarPorCliente(clienteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDtoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping("/cliente/{clienteId}/usuario/{usuarioId}/pedido/{pedidoId}")
    public ResponseEntity<VentaDtoResponse> crear(@PathVariable Long clienteId, @PathVariable Long usuarioId, @PathVariable Long pedidoId) {
        return ResponseEntity.ok(service.crear(clienteId, usuarioId, pedidoId));
    }

    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> actualizarEstado(@PathVariable Long id, @RequestParam Integer estado) {
        service.actualizarEstado(id, estado);
        return ResponseEntity.ok().build();
    }
}
