package idat.pe.NewProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.pe.NewProject.dao.ClienteDao;
import idat.pe.NewProject.dao.ProductoVarianteDao;
import idat.pe.NewProject.dao.WishlistDao;
import idat.pe.NewProject.dao.WishlistProductoDao;
import idat.pe.NewProject.dto.ProductoVarianteDtoResponse;
import idat.pe.NewProject.dto.WishlistDtoResponse;
import idat.pe.NewProject.model.Wishlist;
import idat.pe.NewProject.model.WishlistProducto;
import jakarta.persistence.EntityNotFoundException;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired private WishlistDao dao;
    @Autowired private WishlistProductoDao wishlistProductoDao;
    @Autowired private ClienteDao clienteDao;
    @Autowired private ProductoVarianteDao varianteDao;

    @Override
    public List<WishlistDtoResponse> listarPorCliente(Long clienteId) {
        return dao.findByCliente_Id(clienteId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public WishlistDtoResponse crear(Long clienteId, String nombre) {
        Wishlist w = new Wishlist();
        w.setCliente(clienteDao.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado")));
        w.setNombre(nombre);
        return toDto(dao.save(w));
    }

    @Override
    public void agregarProducto(Long wishlistId, Long productoVarianteId) {
        WishlistProducto wp = new WishlistProducto();
        wp.setWishlist(dao.findById(wishlistId)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist no encontrada")));
        wp.setProductoVariante(varianteDao.findById(productoVarianteId)
                .orElseThrow(() -> new EntityNotFoundException("Variante no encontrada")));
        wishlistProductoDao.save(wp);
    }

    @Override
    public void eliminarProducto(Long wishlistProductoId) {
        wishlistProductoDao.deleteById(wishlistProductoId);
    }

    private WishlistDtoResponse toDto(Wishlist w) {
        WishlistDtoResponse dto = new WishlistDtoResponse();
        dto.setIdWishlist(w.getIdWishlist());
        dto.setClienteId(w.getCliente() != null ? w.getCliente().getId() : null);
        dto.setClienteNombre(w.getCliente() != null ? w.getCliente().getNombre() : null);
        dto.setNombre(w.getNombre());
        dto.setCreatedAt(w.getCreatedAt());

        List<WishlistProducto> productos = wishlistProductoDao.findByWishlist_IdWishlist(w.getIdWishlist());
        dto.setProductos(productos.stream().map(p -> {
            ProductoVarianteDtoResponse pv = new ProductoVarianteDtoResponse();
            if (p.getProductoVariante() != null) {
                pv.setIdVariante(p.getProductoVariante().getIdVariante());
                pv.setProductoId(p.getProductoVariante().getProducto() != null
                        ? p.getProductoVariante().getProducto().getId() : null);
                pv.setTalla(p.getProductoVariante().getTalla());
                pv.setColor(p.getProductoVariante().getColor());
                pv.setPrecio(p.getProductoVariante().getPrecio());
                pv.setStock(p.getProductoVariante().getStock());
            }
            return pv;
        }).collect(Collectors.toList()));

        return dto;
    }
}
