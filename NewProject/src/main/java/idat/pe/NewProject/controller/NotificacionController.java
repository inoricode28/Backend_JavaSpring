package idat.pe.NewProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.NotificacionDtoResponse;
import idat.pe.NewProject.service.NotificacionService;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired private NotificacionService service;

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<NotificacionDtoResponse>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.listarPorCliente(clienteId));
    }

    @PutMapping("/{id}/leido")
    public ResponseEntity<Void> marcarLeido(@PathVariable Long id) {
        service.marcarLeido(id);
        return ResponseEntity.ok().build();
    }
}
