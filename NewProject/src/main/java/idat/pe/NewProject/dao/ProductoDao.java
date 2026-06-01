package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Producto;
import java.util.List;
public interface ProductoDao extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria_CodCategoria(Long categoriaId);
}
