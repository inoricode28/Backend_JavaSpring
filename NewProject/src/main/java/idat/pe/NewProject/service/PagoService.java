package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.PagoDtoRequest;
import idat.pe.NewProject.dto.PagoDtoResponse;

public interface PagoService {
    List<PagoDtoResponse> listarPorVenta(Long ventaId);
    PagoDtoResponse crearPago(Long ventaId, PagoDtoRequest request);
}
