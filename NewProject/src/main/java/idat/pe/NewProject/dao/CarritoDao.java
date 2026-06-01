package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Carrito;
import java.util.Optional;
public interface CarritoDao extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByCliente_Id(Long clienteId);
}
