package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.CategoriaDtoRequest;
import idat.pe.NewProject.dto.CategoriaDtoResponse;

public interface CategoriaService {
    List<CategoriaDtoResponse> listar();
    CategoriaDtoResponse obtenerPorId(Long id);
    CategoriaDtoResponse crear(CategoriaDtoRequest request);
    CategoriaDtoResponse actualizar(Long id, CategoriaDtoRequest request);
    void eliminar(Long id);
}
