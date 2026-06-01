package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.pe.NewProject.dao.EnvioDao;
import idat.pe.NewProject.dao.VentaDao;
import idat.pe.NewProject.dto.EnvioDtoResponse;
import idat.pe.NewProject.model.Envio;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EnvioServiceImpl implements EnvioService {

    @Autowired private EnvioDao dao;
    @Autowired private VentaDao ventaDao;

    @Override
    public List<EnvioDtoResponse> listarPorVenta(Long ventaId) {
        return dao.findByVenta_IdVenta(ventaId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public EnvioDtoResponse crearEnvio(Long ventaId, String direccion) {
        Envio e = new Envio();
        e.setVenta(ventaDao.findById(ventaId)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada")));
        e.setDireccion(direccion);
        e.setEstado(1);
        return toDto(dao.save(e));
    }

    @Override
    public void actualizarEstado(Long id, Integer estado) {
        Envio e = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Envio no encontrado"));
        e.setEstado(estado);
        dao.save(e);
    }

    private EnvioDtoResponse toDto(Envio e) {
        EnvioDtoResponse dto = new EnvioDtoResponse();
        dto.setIdEnvio(e.getIdEnvio());
        dto.setVentaId(e.getVenta() != null ? e.getVenta().getIdVenta() : null);
        dto.setDireccion(e.getDireccion());
        dto.setFechaEnvio(e.getFechaEnvio());
        dto.setEstado(e.getEstado());
        dto.setCreatedAt(e.getCreatedAt());
        return dto;
    }
}
