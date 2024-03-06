package examen.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import examen.entities.Empleado;


public interface EmpleadoRespository extends JpaRepository<Empleado, Integer>{
	
	@Query("SELECT e FROM Empleado e WHERE e.idEmpleado NOT IN (SELECT eep.empleado.idEmpleado FROM EmpleadoEnProyecto eep WHERE eep.proyecto.idProyecto = ?1)")
	public List<Empleado> empleadosAsignables(int idProyecto);
	@Query("SELECT e FROM Empleado e WHERE e.idEmpleado NOT IN (SELECT eep.empleado.idEmpleado FROM EmpleadoEnProyecto eep)")
	public List<Empleado> empleadosSinProyecto();
	
	public List<Empleado> findAll();
	
}
