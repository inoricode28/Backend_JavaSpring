package idat.pe.NewProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import idat.pe.NewProject.dto.MetodoPagoDtoResponse;
import idat.pe.NewProject.service.MetodoPagoService;

@RestController
@RequestMapping("/metodos-pago")
public class MetodoPagoController {

    @Autowired private MetodoPagoService service;

    @GetMapping
    public ResponseEntity<List<MetodoPagoDtoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
