package idat.pe.NewProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.ResenaDtoRequest;
import idat.pe.NewProject.dto.ResenaDtoResponse;
import idat.pe.NewProject.service.ResenaService;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired private ResenaService service;

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ResenaDtoResponse>> listarPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(service.listarPorProducto(productoId));
    }

    @PostMapping("/producto/{productoId}/cliente/{clienteId}")
    public ResponseEntity<ResenaDtoResponse> crear(@PathVariable Long productoId, @PathVariable Long clienteId, @RequestBody ResenaDtoRequest request) {
        return ResponseEntity.ok(service.crear(productoId, clienteId, request));
    }
}
