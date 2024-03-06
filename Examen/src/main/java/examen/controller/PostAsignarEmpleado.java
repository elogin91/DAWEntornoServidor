package examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examen.dto.AsignarEmpleadoDto;
import examen.entities.Empleado;
import examen.entities.EmpleadoEnProyecto;
import examen.entities.Proyecto;
import examen.services.EmpleadoEnProyectoService;
import examen.services.EmpleadoService;
import examen.services.ProyectoService;

@RestController
public class PostAsignarEmpleado {
	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private ProyectoService proyectoService;

	@Autowired
	private EmpleadoEnProyectoService empleadoEnProyectoService;

	@PostMapping("/proyecto/{idProyecto}/asignarEmpleado")
	public ResponseEntity<?> buscarEmpleadosAsignables(@PathVariable int idProyecto,
			@RequestBody AsignarEmpleadoDto asignarEmpleadoDto) {
		Proyecto proyecto = proyectoService.buscarUno(idProyecto);
		if (proyecto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado");
		}

		Empleado empleado = empleadoService.buscarUno(asignarEmpleadoDto.getIdEmpleado());
		if (empleado == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
		}

		EmpleadoEnProyecto nuevoEmpleadoAsignado = new EmpleadoEnProyecto();
		nuevoEmpleadoAsignado.setEmpleado(empleado);
		nuevoEmpleadoAsignado.setProyecto(proyecto);
		nuevoEmpleadoAsignado.setDiasPrevistos(asignarEmpleadoDto.getDiasPrevistos());
		nuevoEmpleadoAsignado.setFechaIncorporacion(asignarEmpleadoDto.getFechaIncorporacion());

		empleadoEnProyectoService.asignarEmpleado(nuevoEmpleadoAsignado);

		return ResponseEntity.status(HttpStatus.CREATED).body("Empleado Asignado");
	}
}
