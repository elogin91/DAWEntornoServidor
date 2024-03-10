package vacantes.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vacantes.modelo.entidades.Vacante;

public interface VacanteRepository extends JpaRepository<Vacante, Integer> {

	@Query("Select v FROM Vacante v where v.estatus=CREADA")
	public List<Vacante>buscarVacantesActivas();
	
	@Query("Select v FROM Vacante v where v.estatus=CREADA AND v.categoria.idCategoria=?1")
	public List<Vacante>buscarVacantesCategoria(int categoria);
}
