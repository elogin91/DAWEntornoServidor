package examen.services;

import java.util.List;

import examen.entities.EmpleadoEnProyecto;
import examen.entities.Proyecto;

public interface EmpleadoEnProyectoService {
	List<EmpleadoEnProyecto> buscarPorProyecto(Proyecto proyecto);

	EmpleadoEnProyecto asignarEmpleado(EmpleadoEnProyecto nuevoEmpleadoAsignado);
}
