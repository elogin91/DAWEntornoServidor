package examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import examen.dto.ProyectoDto;
import examen.entities.Proyecto;
import examen.services.ProyectoService;

@RestController
public class PutProyectoController {
	@Autowired
	private ProyectoService proyectoService;

	@PutMapping("/proyecto/{idProyecto}")
	public ResponseEntity<?> modificarProyecto(@PathVariable int idProyecto, ProyectoDto proyectoDto) {
		Proyecto proyecto = proyectoService.buscarUno(idProyecto);
		if (proyecto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
		} else {
			proyecto.setDescripcion(proyectoDto.getDescripcion());
			proyecto.setDiasPrevistos(proyectoDto.getDiasPrevistos());
			proyecto.setFechaInicio(proyectoDto.getFechaInicio());
			proyecto.setEstado(proyectoDto.getEstado());

			Proyecto proyectoUpdated = proyectoService.modificarProyecto(proyecto);
			
			if(proyectoUpdated == null) {
				return ResponseEntity.unprocessableEntity().body("No se ha podido actualizar el proyecto");
			} else {
				return ResponseEntity.ok(proyectoUpdated);
			}
		}
	}
}
