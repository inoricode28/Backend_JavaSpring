package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.ProductoVarianteDtoRequest;
import idat.pe.NewProject.dto.ProductoVarianteDtoResponse;

public interface ProductoVarianteService {
    List<ProductoVarianteDtoResponse> listarPorProducto(Long productoId);
    ProductoVarianteDtoResponse obtenerPorId(Long id);
    ProductoVarianteDtoResponse crear(Long productoId, ProductoVarianteDtoRequest request);
    ProductoVarianteDtoResponse actualizar(Long id, ProductoVarianteDtoRequest request);
    void eliminar(Long id);
}
