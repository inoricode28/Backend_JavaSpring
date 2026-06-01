package idat.pe.NewProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.ProductoVarianteDtoRequest;
import idat.pe.NewProject.dto.ProductoVarianteDtoResponse;
import idat.pe.NewProject.service.ProductoVarianteService;

@RestController
@RequestMapping("/productos/{productoId}/variantes")
public class ProductoVarianteController {

    @Autowired private ProductoVarianteService service;

    @GetMapping
    public ResponseEntity<List<ProductoVarianteDtoResponse>> listar(@PathVariable Long productoId) {
        return ResponseEntity.ok(service.listarPorProducto(productoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoVarianteDtoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductoVarianteDtoResponse> crear(@PathVariable Long productoId, @RequestBody ProductoVarianteDtoRequest request) {
        return ResponseEntity.ok(service.crear(productoId, request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductoVarianteDtoResponse> actualizar(@PathVariable Long id, @RequestBody ProductoVarianteDtoRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
