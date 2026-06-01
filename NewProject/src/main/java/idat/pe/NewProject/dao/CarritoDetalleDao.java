package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.CarritoDetalle;
import java.util.List;
public interface CarritoDetalleDao extends JpaRepository<CarritoDetalle, Long> {
    List<CarritoDetalle> findByCarrito_IdCarrito(Long carritoId);
}
