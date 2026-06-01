package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.ClienteDtoRequest;
import idat.pe.NewProject.dto.ClienteDtoResponse;

public interface ClienteService {
    List<ClienteDtoResponse> listar();
    ClienteDtoResponse obtenerPorId(Long id);
    ClienteDtoResponse crear(ClienteDtoRequest request, Long usuarioId);
    ClienteDtoResponse actualizar(Long id, ClienteDtoRequest request);
    void eliminar(Long id);
}
