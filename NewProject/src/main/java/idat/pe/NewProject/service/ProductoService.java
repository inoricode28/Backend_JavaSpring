package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.ProductoDtoRequest;
import idat.pe.NewProject.dto.ProductoDtoResponse;

public interface ProductoService {
    List<ProductoDtoResponse> listar();
    List<ProductoDtoResponse> listarPorCategoria(Long categoriaId);
    ProductoDtoResponse obtenerPorId(Long id);
    ProductoDtoResponse crear(ProductoDtoRequest request);
    ProductoDtoResponse actualizar(Long id, ProductoDtoRequest request);
    void eliminar(Long id);
}
