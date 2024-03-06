package examen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import examen.entities.Empleado;

@Service
public interface EmpleadoService {

	public List<Empleado> mostrarEmpleadosAsignables(int idProyecto);
	public Empleado buscarUno(int idEmpleado);
}
