package idat.pe.NewProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.WishlistDtoResponse;
import idat.pe.NewProject.service.WishlistService;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired private WishlistService service;

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<WishlistDtoResponse>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.listarPorCliente(clienteId));
    }

    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<WishlistDtoResponse> crear(@PathVariable Long clienteId, @RequestParam(required = false) String nombre) {
        return ResponseEntity.ok(service.crear(clienteId, nombre));
    }

    @PostMapping("/{wishlistId}/producto/{productoVarianteId}")
    public ResponseEntity<Void> agregarProducto(@PathVariable Long wishlistId, @PathVariable Long productoVarianteId) {
        service.agregarProducto(wishlistId, productoVarianteId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/producto/{wishlistProductoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long wishlistProductoId) {
        service.eliminarProducto(wishlistProductoId);
        return ResponseEntity.noContent().build();
    }
}
