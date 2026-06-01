package idat.pe.NewProject.service;

import java.util.List;
import idat.pe.NewProject.dto.WishlistDtoResponse;

public interface WishlistService {
    List<WishlistDtoResponse> listarPorCliente(Long clienteId);
    WishlistDtoResponse crear(Long clienteId, String nombre);
    void agregarProducto(Long wishlistId, Long productoVarianteId);
    void eliminarProducto(Long wishlistProductoId);
}
