package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.pe.NewProject.dao.NotificacionDao;
import idat.pe.NewProject.dto.NotificacionDtoResponse;
import idat.pe.NewProject.model.Notificacion;
import jakarta.persistence.EntityNotFoundException;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired private NotificacionDao dao;

    @Override
    public List<NotificacionDtoResponse> listarPorCliente(Long clienteId) {
        return dao.findByCliente_Id(clienteId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void marcarLeido(Long id) {
        Notificacion n = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Notificacion no encontrada"));
        n.setLeido(1);
        dao.save(n);
    }

    private NotificacionDtoResponse toDto(Notificacion n) {
        NotificacionDtoResponse dto = new NotificacionDtoResponse();
        dto.setIdNotificacion(n.getIdNotificacion());
        dto.setClienteId(n.getCliente() != null ? n.getCliente().getId() : null);
        dto.setMensaje(n.getMensaje());
        dto.setTipo(n.getTipo());
        dto.setLeido(n.getLeido());
        dto.setCreatedAt(n.getCreatedAt());
        return dto;
    }
}
