package vacantes.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vacantes.modelo.entidades.Perfil;
import java.util.Optional;



@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
	Optional<Perfil>findByNombre(String nombre);
}
