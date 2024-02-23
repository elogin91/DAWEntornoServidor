package vacantes.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vacantes.modelo.entidades.Categoria;
import vacantes.modelo.entidades.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

}
