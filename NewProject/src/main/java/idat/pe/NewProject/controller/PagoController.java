package idat.pe.NewProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.PagoDtoRequest;
import idat.pe.NewProject.dto.PagoDtoResponse;
import idat.pe.NewProject.service.PagoService;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired private PagoService service;

    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<PagoDtoResponse>> listarPorVenta(@PathVariable Long ventaId) {
        return ResponseEntity.ok(service.listarPorVenta(ventaId));
    }

    @PostMapping("/venta/{ventaId}")
    public ResponseEntity<PagoDtoResponse> crearPago(@PathVariable Long ventaId, @RequestBody PagoDtoRequest request) {
        return ResponseEntity.ok(service.crearPago(ventaId, request));
    }
}
