package vacantes.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vacantes.modelo.entidades.Categoria;
import vacantes.modelo.entidades.Vacante;

public interface VacanteRepository extends JpaRepository<Vacante, Integer> {

}
