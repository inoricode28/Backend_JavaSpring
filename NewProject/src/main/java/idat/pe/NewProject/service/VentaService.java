package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.VentaDtoResponse;

public interface VentaService {
    List<VentaDtoResponse> listar();
    List<VentaDtoResponse> listarPorCliente(Long clienteId);
    VentaDtoResponse obtenerPorId(Long id);
    VentaDtoResponse crear(Long clienteId, Long usuarioId, Long pedidoId);
    void actualizarEstado(Long id, Integer estado);
}
