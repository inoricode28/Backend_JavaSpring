package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.ProductoVariante;
import java.util.List;
public interface ProductoVarianteDao extends JpaRepository<ProductoVariante, Long> {
    List<ProductoVariante> findByProducto_Id(Long productoId);
}
