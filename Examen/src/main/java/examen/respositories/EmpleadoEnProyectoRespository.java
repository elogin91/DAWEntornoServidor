package examen.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import examen.entities.EmpleadoEnProyecto;
import examen.entities.Proyecto;

public interface EmpleadoEnProyectoRespository extends JpaRepository<EmpleadoEnProyecto, Integer>{
	public List<EmpleadoEnProyecto> findAllByProyecto(Proyecto proyecto);
}
