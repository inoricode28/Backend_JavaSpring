package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import idat.pe.NewProject.dao.CategoriaDao;
import idat.pe.NewProject.dto.CategoriaDtoRequest;
import idat.pe.NewProject.dto.CategoriaDtoResponse;
import idat.pe.NewProject.model.Categoria;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired private CategoriaDao dao;

    @Override
    public List<CategoriaDtoResponse> listar() {
        return dao.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoriaDtoResponse obtenerPorId(Long id) {
        return toDto(dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada")));
    }

    @Override
    public CategoriaDtoResponse crear(CategoriaDtoRequest request) {
        Categoria c = new Categoria();
        c.setNombre(request.getNombre());
        c.setEstado(1);
        return toDto(dao.save(c));
    }

    @Override
    public CategoriaDtoResponse actualizar(Long id, CategoriaDtoRequest request) {
        Categoria c = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
        c.setNombre(request.getNombre());
        return toDto(dao.save(c));
    }

    @Override
    public void eliminar(Long id) {
        Categoria c = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
        c.setEstado(0);
        dao.save(c);
    }

    private CategoriaDtoResponse toDto(Categoria c) {
        CategoriaDtoResponse dto = new CategoriaDtoResponse();
        dto.setCodCategoria(c.getCodCategoria());
        dto.setNombre(c.getNombre());
        dto.setEstado(c.getEstado());
        dto.setCreatedAt(c.getCreatedAt());
        return dto;
    }
}
