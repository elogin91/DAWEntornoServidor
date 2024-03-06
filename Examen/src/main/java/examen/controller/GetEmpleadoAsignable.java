package examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import examen.entities.Empleado;
import examen.services.EmpleadoService;
import examen.services.ProyectoService;

@RestController
public class GetEmpleadoAsignable {
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private ProyectoService proyectoService;

	@GetMapping("/proyecto/{idProyecto}/asignarEmpleado")
	public ResponseEntity<?> buscarEmpleadosAsignables(@PathVariable int idProyecto) {
		if(proyectoService.buscarUno(idProyecto) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe proyecyto");
		} else  {
			List<Empleado> empleadosAsignables = empleadoService.mostrarEmpleadosAsignables(idProyecto);
			return ResponseEntity.status(200).body(empleadosAsignables);
		}
	}
}
