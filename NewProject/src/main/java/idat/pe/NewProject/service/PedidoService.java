package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.PedidoDtoRequest;
import idat.pe.NewProject.dto.PedidoDtoResponse;

public interface PedidoService {
    List<PedidoDtoResponse> listarPorCliente(Long clienteId);
    PedidoDtoResponse obtenerPorId(Long id);
    PedidoDtoResponse crear(Long clienteId, PedidoDtoRequest request);
    PedidoDtoResponse actualizarEstado(Long id, Integer estado);
}
