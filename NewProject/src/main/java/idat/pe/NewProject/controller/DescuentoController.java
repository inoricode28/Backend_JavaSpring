package idat.pe.NewProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.DescuentoDtoRequest;
import idat.pe.NewProject.dto.DescuentoDtoResponse;
import idat.pe.NewProject.service.DescuentoService;

@RestController
@RequestMapping("/admin/descuentos")
@PreAuthorize("hasRole('ADMIN')")
public class DescuentoController {

    @Autowired private DescuentoService service;

    @GetMapping
    public ResponseEntity<List<DescuentoDtoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescuentoDtoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<DescuentoDtoResponse> crear(@RequestBody DescuentoDtoRequest request) {
        return ResponseEntity.ok(service.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescuentoDtoResponse> actualizar(@PathVariable Long id, @RequestBody DescuentoDtoRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
