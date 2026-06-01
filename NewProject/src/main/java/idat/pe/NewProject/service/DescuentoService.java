package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.DescuentoDtoRequest;
import idat.pe.NewProject.dto.DescuentoDtoResponse;

public interface DescuentoService {
    List<DescuentoDtoResponse> listar();
    DescuentoDtoResponse obtenerPorId(Long id);
    DescuentoDtoResponse crear(DescuentoDtoRequest request);
    DescuentoDtoResponse actualizar(Long id, DescuentoDtoRequest request);
    void eliminar(Long id);
}
