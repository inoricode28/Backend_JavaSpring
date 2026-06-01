package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.NotificacionDtoResponse;

public interface NotificacionService {
    List<NotificacionDtoResponse> listarPorCliente(Long clienteId);
    void marcarLeido(Long id);
}
