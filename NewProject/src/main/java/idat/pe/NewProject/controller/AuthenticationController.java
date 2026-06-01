package idat.pe.NewProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idat.pe.NewProject.dto.LoginDtoRequest;
import idat.pe.NewProject.dto.UsuarioDtoResponse;
import idat.pe.NewProject.dto.UsuarioRegistroDtoRequest;
import idat.pe.NewProject.service.AutenticacionService;
import idat.pe.NewProject.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AutenticacionService authServ;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDtoRequest loginDto) {
        return ResponseEntity.ok(authServ.autenticar(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDtoResponse> register(@RequestBody UsuarioRegistroDtoRequest request) {
        return ResponseEntity.ok(usuarioService.registrar(request));
    }
}
