package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.pe.NewProject.dao.DescuentoDao;
import idat.pe.NewProject.dto.DescuentoDtoRequest;
import idat.pe.NewProject.dto.DescuentoDtoResponse;
import idat.pe.NewProject.model.Descuento;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DescuentoServiceImpl implements DescuentoService {

    @Autowired private DescuentoDao dao;

    @Override
    public List<DescuentoDtoResponse> listar() {
        return dao.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public DescuentoDtoResponse obtenerPorId(Long id) {
        return toDto(dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Descuento no encontrado")));
    }

    @Override
    public DescuentoDtoResponse crear(DescuentoDtoRequest request) {
        Descuento d = new Descuento();
        d.setNombre(request.getNombre());
        d.setDescripcion(request.getDescripcion());
        d.setPorcentaje(request.getPorcentaje());
        d.setFechaInicio(request.getFechaInicio());
        d.setFechaFin(request.getFechaFin());
        d.setEstado(1);
        return toDto(dao.save(d));
    }

    @Override
    public DescuentoDtoResponse actualizar(Long id, DescuentoDtoRequest request) {
        Descuento d = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Descuento no encontrado"));
        if (request.getNombre() != null) d.setNombre(request.getNombre());
        if (request.getDescripcion() != null) d.setDescripcion(request.getDescripcion());
        if (request.getPorcentaje() != null) d.setPorcentaje(request.getPorcentaje());
        if (request.getFechaInicio() != null) d.setFechaInicio(request.getFechaInicio());
        if (request.getFechaFin() != null) d.setFechaFin(request.getFechaFin());
        return toDto(dao.save(d));
    }

    @Override
    public void eliminar(Long id) {
        Descuento d = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Descuento no encontrado"));
        d.setEstado(0);
        dao.save(d);
    }

    private DescuentoDtoResponse toDto(Descuento d) {
        DescuentoDtoResponse dt = new DescuentoDtoResponse();
        dt.setIdDescuento(d.getIdDescuento());
        dt.setNombre(d.getNombre());
        dt.setDescripcion(d.getDescripcion());
        dt.setPorcentaje(d.getPorcentaje());
        dt.setFechaInicio(d.getFechaInicio());
        dt.setFechaFin(d.getFechaFin());
        dt.setEstado(d.getEstado());
        dt.setCreatedAt(d.getCreatedAt());
        return dt;
    }
}
