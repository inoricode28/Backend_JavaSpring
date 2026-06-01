package idat.pe.NewProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.CarritoDetalleDtoRequest;
import idat.pe.NewProject.dto.CarritoDtoResponse;
import idat.pe.NewProject.service.CarritoService;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired private CarritoService service;

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<CarritoDtoResponse> obtener(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.obtenerPorCliente(clienteId));
    }

    @PostMapping("/cliente/{clienteId}/agregar")
    public ResponseEntity<CarritoDtoResponse> agregar(@PathVariable Long clienteId, @RequestBody CarritoDetalleDtoRequest request) {
        return ResponseEntity.ok(service.agregarProducto(clienteId, request));
    }

    @DeleteMapping("/detalle/{detalleId}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long detalleId) {
        service.eliminarDetalle(detalleId);
        return ResponseEntity.noContent().build();
    }
}
