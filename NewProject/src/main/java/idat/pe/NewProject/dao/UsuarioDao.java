package idat.pe.NewProject.dao;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import idat.pe.NewProject.model.Usuario;
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);
}
