package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.EnvioDtoResponse;

public interface EnvioService {
    List<EnvioDtoResponse> listarPorVenta(Long ventaId);
    EnvioDtoResponse crearEnvio(Long ventaId, String direccion);
    void actualizarEstado(Long id, Integer estado);
}
