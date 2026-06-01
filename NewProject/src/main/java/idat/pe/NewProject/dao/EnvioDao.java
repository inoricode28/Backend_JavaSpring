package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Envio;
import java.util.List;
public interface EnvioDao extends JpaRepository<Envio, Long> {
    List<Envio> findByVenta_IdVenta(Long ventaId);
}
