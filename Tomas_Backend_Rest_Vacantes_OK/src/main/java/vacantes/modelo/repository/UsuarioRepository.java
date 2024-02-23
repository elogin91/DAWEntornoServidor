package vacantes.modelo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vacantes.modelo.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	  Optional<Usuario> findByUsername(String username);
	  Boolean existsByUsername(String username);
	  Boolean existsByEmail(String email);
}
