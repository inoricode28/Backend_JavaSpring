package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Pedido;
import java.util.List;
public interface PedidoDao extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCliente_Id(Long clienteId);
}
