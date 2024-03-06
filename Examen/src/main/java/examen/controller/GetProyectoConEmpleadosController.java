package examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import examen.dto.EmpleadoDto;
import examen.dto.ProyectoConEmpleadosDto;
import examen.entities.EmpleadoEnProyecto;
import examen.entities.Proyecto;
import examen.services.EmpleadoEnProyectoService;
import examen.services.ProyectoService;

@RestController
public class GetProyectoConEmpleadosController {
	@Autowired
	private ProyectoService proyectoService;

	@Autowired
	private EmpleadoEnProyectoService empleadoEnProyectoService;

	@GetMapping("/proyectoConEmpleados/{idProyecto}")
	public ResponseEntity<?> buscarUno(@PathVariable int idProyecto) {

		Proyecto proyecto = proyectoService.buscarUno(idProyecto);
		if (proyecto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proyecto no encontrado");
		} else {
			List<EmpleadoEnProyecto> empleadosEnProyectos = empleadoEnProyectoService.buscarPorProyecto(proyecto);

			ProyectoConEmpleadosDto proyectoConEmpleadosDto = new ProyectoConEmpleadosDto();
			proyectoConEmpleadosDto.setIdProyecto(proyecto.getIdProyecto());
			proyectoConEmpleadosDto.setDescripcion(proyecto.getDescripcion());
			proyectoConEmpleadosDto.setFechaInicio(proyecto.getFechaInicio());
			// TODO proyectoConEmpleadosDto.setFechaFin(// FechaInicio + DiasPrevistos);
			proyectoConEmpleadosDto.setDiasPrevistos(proyecto.getDiasPrevistos());
			proyectoConEmpleadosDto.setEstado(proyecto.getEstado());

			proyectoConEmpleadosDto.setEmpleados(empleadosEnProyectos.stream().map((empleadoEnProyecto) -> {
				EmpleadoDto empleadoDto = new EmpleadoDto();
				empleadoDto.setNombre(empleadoEnProyecto.getEmpleado().getNombre());
				empleadoDto.setFechaInicio(empleadoEnProyecto.getFechaIncorporacion());
				empleadoDto.setDiasAsignados(empleadoEnProyecto.getDiasPrevistos());
				return empleadoDto;
			}).toList());
			return ResponseEntity.ok(proyectoConEmpleadosDto);
		}
	}
}
