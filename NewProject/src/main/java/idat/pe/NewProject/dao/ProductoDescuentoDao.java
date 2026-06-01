package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.ProductoDescuento;
import java.util.List;
public interface ProductoDescuentoDao extends JpaRepository<ProductoDescuento, Long> {
    List<ProductoDescuento> findByProducto_Id(Long productoId);
}
