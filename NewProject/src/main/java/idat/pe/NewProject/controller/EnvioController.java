package idat.pe.NewProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.EnvioDtoResponse;
import idat.pe.NewProject.service.EnvioService;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    @Autowired private EnvioService service;

    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<EnvioDtoResponse>> listarPorVenta(@PathVariable Long ventaId) {
        return ResponseEntity.ok(service.listarPorVenta(ventaId));
    }

    @PostMapping("/venta/{ventaId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EnvioDtoResponse> crearEnvio(@PathVariable Long ventaId, @RequestParam String direccion) {
        return ResponseEntity.ok(service.crearEnvio(ventaId, direccion));
    }

    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> actualizarEstado(@PathVariable Long id, @RequestParam Integer estado) {
        service.actualizarEstado(id, estado);
        return ResponseEntity.ok().build();
    }
}
