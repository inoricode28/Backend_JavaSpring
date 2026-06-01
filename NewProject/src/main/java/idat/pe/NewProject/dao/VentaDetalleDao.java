package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.VentaDetalle;
import java.util.List;
public interface VentaDetalleDao extends JpaRepository<VentaDetalle, Long> {
    List<VentaDetalle> findByVenta_IdVenta(Long ventaId);
}
