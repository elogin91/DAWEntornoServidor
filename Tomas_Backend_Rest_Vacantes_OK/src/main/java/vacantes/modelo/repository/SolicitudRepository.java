package vacantes.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import vacantes.modelo.entidades.Solicitud;
import vacantes.modelo.entidades.Usuario;
import vacantes.modelo.entidades.Vacante;

import java.util.List;


@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
	List<Solicitud> findByUsuario(Usuario usuario);
	List<Solicitud> findByVacante(Vacante vacante);
	
	@Query("Select s FROM Solicitud s where s.usuario.username=?1 and s.vacante.idVacante=?2")
	Solicitud findByUsuarioAndVacante(String username, int idVacante);
}
