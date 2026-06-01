package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.ResenaDtoRequest;
import idat.pe.NewProject.dto.ResenaDtoResponse;

public interface ResenaService {
    List<ResenaDtoResponse> listarPorProducto(Long productoId);
    ResenaDtoResponse crear(Long productoId, Long clienteId, ResenaDtoRequest request);
}
