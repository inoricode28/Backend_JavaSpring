package idat.pe.NewProject.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import idat.pe.NewProject.dto.ProductoDtoRequest;
import idat.pe.NewProject.dto.ProductoDtoResponse;
import idat.pe.NewProject.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired private ProductoService service;

    @GetMapping
    public ResponseEntity<List<ProductoDtoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoDtoResponse>> listarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(service.listarPorCategoria(categoriaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDtoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductoDtoResponse> crear(
            @RequestParam("categoriaId") Long categoriaId,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "marca", required = false) String marca,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam("costo") Float costo,
            @RequestParam("precioMin") BigDecimal precioMin,
            @RequestParam(value = "precioMax", required = false) BigDecimal precioMax,
            @RequestParam("stock") Integer stock,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        ProductoDtoRequest request = new ProductoDtoRequest();
        request.setCategoriaId(categoriaId);
        request.setNombre(nombre);
        request.setMarca(marca);
        request.setDescripcion(descripcion);
        request.setCosto(costo);
        request.setPrecioMin(precioMin);
        request.setPrecioMax(precioMax);
        request.setStock(stock);

        if (file != null && !file.isEmpty()) {
            request.setImagen(guardarImagen(file));
        }

        return ResponseEntity.ok(service.crear(request));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductoDtoResponse> actualizar(
            @PathVariable Long id,
            @RequestParam("categoriaId") Long categoriaId,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "marca", required = false) String marca,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam("costo") Float costo,
            @RequestParam("precioMin") BigDecimal precioMin,
            @RequestParam(value = "precioMax", required = false) BigDecimal precioMax,
            @RequestParam("stock") Integer stock,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "imagenActual", required = false) String imagenActual) {

        ProductoDtoRequest request = new ProductoDtoRequest();
        request.setCategoriaId(categoriaId);
        request.setNombre(nombre);
        request.setMarca(marca);
        request.setDescripcion(descripcion);
        request.setCosto(costo);
        request.setPrecioMin(precioMin);
        request.setPrecioMax(precioMax);
        request.setStock(stock);

        if (file != null && !file.isEmpty()) {
            request.setImagen(guardarImagen(file));
        } else {
            request.setImagen(imagenActual);
        }

        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private String guardarImagen(MultipartFile file) {
        try {
            String originalName = file.getOriginalFilename();
            String extension = "";
            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }
            String fileName = UUID.randomUUID().toString() + extension;

            Path uploadPath = Paths.get("uploads/img/");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/newproject/uploads/img/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }
    }
}
