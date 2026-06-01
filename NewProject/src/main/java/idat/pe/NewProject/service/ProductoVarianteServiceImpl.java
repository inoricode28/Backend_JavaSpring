package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import idat.pe.NewProject.dao.ProductoDao;
import idat.pe.NewProject.dao.ProductoVarianteDao;
import idat.pe.NewProject.dto.ProductoVarianteDtoRequest;
import idat.pe.NewProject.dto.ProductoVarianteDtoResponse;
import idat.pe.NewProject.model.ProductoVariante;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoVarianteServiceImpl implements ProductoVarianteService {

    @Autowired private ProductoVarianteDao dao;
    @Autowired private ProductoDao productoDao;

    @Override
    public List<ProductoVarianteDtoResponse> listarPorProducto(Long productoId) {
        return dao.findByProducto_Id(productoId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductoVarianteDtoResponse obtenerPorId(Long id) {
        return toDto(dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Variante no encontrada")));
    }

    @Override
    public ProductoVarianteDtoResponse crear(Long productoId, ProductoVarianteDtoRequest request) {
        ProductoVariante v = new ProductoVariante();
        v.setProducto(productoDao.findById(productoId).orElse(null));
        v.setTalla(request.getTalla());
        v.setColor(request.getColor());
        v.setPrecio(request.getPrecio());
        v.setStock(request.getStock());
        return toDto(dao.save(v));
    }

    @Override
    public ProductoVarianteDtoResponse actualizar(Long id, ProductoVarianteDtoRequest request) {
        ProductoVariante v = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Variante no encontrada"));
        if (request.getTalla() != null) v.setTalla(request.getTalla());
        if (request.getColor() != null) v.setColor(request.getColor());
        if (request.getPrecio() != null) v.setPrecio(request.getPrecio());
        if (request.getStock() != null) v.setStock(request.getStock());
        return toDto(dao.save(v));
    }

    @Override
    public void eliminar(Long id) {
        dao.deleteById(id);
    }

    private ProductoVarianteDtoResponse toDto(ProductoVariante v) {
        ProductoVarianteDtoResponse dto = new ProductoVarianteDtoResponse();
        dto.setIdVariante(v.getIdVariante());
        dto.setProductoId(v.getProducto() != null ? v.getProducto().getId() : null);
        dto.setTalla(v.getTalla());
        dto.setColor(v.getColor());
        dto.setPrecio(v.getPrecio());
        dto.setStock(v.getStock());
        return dto;
    }
}
