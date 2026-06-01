package idat.pe.NewProject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Cliente;
public interface ClienteDao extends JpaRepository<Cliente, Long> {}
