package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import idat.pe.NewProject.config.jwt.JwtUtils;
import idat.pe.NewProject.dto.JwtDtoResponse;
import idat.pe.NewProject.dto.LoginDtoRequest;
import idat.pe.NewProject.dto.UsuarioDtoResponse;

@Component
public class AutenticacionService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtutils;

    public JwtDtoResponse autenticar(LoginDtoRequest loginDto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsuario(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtutils.generateJwtToken(auth);
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        UsuarioDtoResponse dtoU = new UsuarioDtoResponse();
        dtoU.setId(userDetails.getId());
        dtoU.setUsuario(userDetails.getUsuario());
        dtoU.setRol(userDetails.getRolNombre());
        return new JwtDtoResponse(jwt, dtoU);
    }
}
