package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.PedidoDetalle;
import java.util.List;
public interface PedidoDetalleDao extends JpaRepository<PedidoDetalle, Long> {
    List<PedidoDetalle> findByPedido_IdPedido(Long pedidoId);
}
