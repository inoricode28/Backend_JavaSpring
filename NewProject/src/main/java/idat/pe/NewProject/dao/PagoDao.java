package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Pago;
import java.util.List;
public interface PagoDao extends JpaRepository<Pago, Long> {
    List<Pago> findByVenta_IdVenta(Long ventaId);
}
