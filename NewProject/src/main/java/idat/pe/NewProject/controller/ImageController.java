package idat.pe.NewProject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uploads")
public class ImageController {

    private static final String UPLOAD_DIR = "uploads/img/";

    @PostMapping("/imagen")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> subirImagen(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\":\"Archivo vacio\"}");
        }

        try {
            String originalName = file.getOriginalFilename();
            String extension = "";
            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }
            String fileName = UUID.randomUUID().toString() + extension;

            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "/newproject/uploads/img/" + fileName;
            return ResponseEntity.ok("{\"imagen\":\"" + imageUrl + "\"}");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("{\"error\":\"Error al subir imagen: " + e.getMessage() + "\"}");
        }
    }
}
