package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import idat.pe.NewProject.dao.CategoriaDao;
import idat.pe.NewProject.dao.ProductoDao;
import idat.pe.NewProject.dto.ProductoDtoRequest;
import idat.pe.NewProject.dto.ProductoDtoResponse;
import idat.pe.NewProject.model.Producto;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired private ProductoDao dao;
    @Autowired private CategoriaDao categoriaDao;

    @Override
    public List<ProductoDtoResponse> listar() {
        return dao.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDtoResponse> listarPorCategoria(Long categoriaId) {
        return dao.findByCategoria_CodCategoria(categoriaId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductoDtoResponse obtenerPorId(Long id) {
        return toDto(dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado")));
    }

    @Override
    public ProductoDtoResponse crear(ProductoDtoRequest request) {
        Producto p = new Producto();
        p.setCategoria(categoriaDao.findById(request.getCategoriaId()).orElse(null));
        p.setNombre(request.getNombre());
        p.setMarca(request.getMarca());
        p.setDescripcion(request.getDescripcion());
        p.setCosto(request.getCosto());
        p.setPrecioMin(request.getPrecioMin());
        p.setPrecioMax(request.getPrecioMax());
        p.setStock(request.getStock());
        p.setEstado(1);
        p.setImagen(request.getImagen());
        return toDto(dao.save(p));
    }

    @Override
    public ProductoDtoResponse actualizar(Long id, ProductoDtoRequest request) {
        Producto p = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        if (request.getCategoriaId() != null)
            p.setCategoria(categoriaDao.findById(request.getCategoriaId()).orElse(null));
        if (request.getNombre() != null) p.setNombre(request.getNombre());
        if (request.getMarca() != null) p.setMarca(request.getMarca());
        if (request.getDescripcion() != null) p.setDescripcion(request.getDescripcion());
        if (request.getCosto() != null) p.setCosto(request.getCosto());
        if (request.getPrecioMin() != null) p.setPrecioMin(request.getPrecioMin());
        if (request.getPrecioMax() != null) p.setPrecioMax(request.getPrecioMax());
        if (request.getStock() != null) p.setStock(request.getStock());
        if (request.getImagen() != null) p.setImagen(request.getImagen());
        return toDto(dao.save(p));
    }

    @Override
    public void eliminar(Long id) {
        Producto p = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        p.setEstado(0);
        dao.save(p);
    }

    private ProductoDtoResponse toDto(Producto p) {
        ProductoDtoResponse dto = new ProductoDtoResponse();
        dto.setId(p.getId());
        dto.setCategoriaId(p.getCategoria() != null ? p.getCategoria().getCodCategoria() : null);
        dto.setCategoriaNombre(p.getCategoria() != null ? p.getCategoria().getNombre() : null);
        dto.setNombre(p.getNombre());
        dto.setMarca(p.getMarca());
        dto.setDescripcion(p.getDescripcion());
        dto.setCosto(p.getCosto());
        dto.setPrecioMin(p.getPrecioMin());
        dto.setPrecioMax(p.getPrecioMax());
        dto.setStock(p.getStock());
        dto.setEstado(p.getEstado());
        dto.setImagen(p.getImagen());
        dto.setCreatedAt(p.getCreatedAt());
        return dto;
    }
}
