package vacantes.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import vacantes.modelo.entidades.Solicitud;
import vacantes.modelo.entidades.Usuario;

import java.util.List;


@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
	List<Solicitud> findByUsuario(Usuario usuario);
}
