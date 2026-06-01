package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Wishlist;
import java.util.List;
public interface WishlistDao extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByCliente_Id(Long clienteId);
}
