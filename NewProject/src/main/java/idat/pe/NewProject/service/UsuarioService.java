package idat.pe.NewProject.service;

import idat.pe.NewProject.dto.UsuarioDtoResponse;
import idat.pe.NewProject.dto.UsuarioRegistroDtoRequest;

public interface UsuarioService {

	UsuarioDtoResponse registrar(UsuarioRegistroDtoRequest usuario);
	
	
}
