package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.WishlistProducto;
import java.util.List;
public interface WishlistProductoDao extends JpaRepository<WishlistProducto, Long> {
    List<WishlistProducto> findByWishlist_IdWishlist(Long wishlistId);
}
