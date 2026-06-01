package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.pe.NewProject.dao.MetodoPagoDao;
import idat.pe.NewProject.dto.MetodoPagoDtoResponse;
import idat.pe.NewProject.model.MetodoPago;

@Service
public class MetodoPagoServiceImpl implements MetodoPagoService {

    @Autowired private MetodoPagoDao dao;

    @Override
    public List<MetodoPagoDtoResponse> listar() {
        return dao.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private MetodoPagoDtoResponse toDto(MetodoPago m) {
        MetodoPagoDtoResponse dto = new MetodoPagoDtoResponse();
        dto.setIdMetodoPago(m.getIdMetodoPago());
        dto.setNombre(m.getNombre());
        dto.setEstado(m.getEstado());
        dto.setCreatedAt(m.getCreatedAt());
        return dto;
    }
}
