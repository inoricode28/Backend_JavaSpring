package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Venta;
import java.util.List;
public interface VentaDao extends JpaRepository<Venta, Long> {
    List<Venta> findByCliente_Id(Long clienteId);
}
