package examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import examen.dto.ProyectoDto;
import examen.entities.Proyecto;
import examen.services.ProyectoService;

@RestController
public class GetProyectoController {
	@Autowired
	private ProyectoService proyectoService;

	@GetMapping("/proyecto/{idProyecto}")
	public ResponseEntity<?> buscarUno(@PathVariable int idProyecto) {
		Proyecto proyecto = proyectoService.buscarUno(idProyecto);
		if (proyecto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
		} else {
			ProyectoDto proyectoDto = new ProyectoDto();
			proyectoDto.setIdProyecto(proyecto.getIdProyecto());
			proyectoDto.setDescripcion(proyecto.getDescripcion());
			proyectoDto.setFechaInicio(proyecto.getFechaInicio());
			// TODO proyectoDto.setFechaFin(// FechaInicio + DiasPrevistos);
			proyectoDto.setDiasPrevistos(proyecto.getDiasPrevistos());
			proyectoDto.setEstado(proyecto.getEstado());
			return ResponseEntity.ok(proyectoDto);
		}
	}
}
