package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Resena;
import java.util.List;
public interface ResenaDao extends JpaRepository<Resena, Long> {
    List<Resena> findByProducto_Id(Long productoId);
}
