package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Notificacion;
import java.util.List;
public interface NotificacionDao extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByCliente_Id(Long clienteId);
}
