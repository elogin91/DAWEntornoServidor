package examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examen.entities.EmpleadoEnProyecto;
import examen.entities.Proyecto;
import examen.respositories.EmpleadoEnProyectoRespository;


@Service
public class EmpleadoEnProyectoServiceImpl implements EmpleadoEnProyectoService{
	@Autowired
	EmpleadoEnProyectoRespository empleadoEnProyectoRespository;


	@Override
	public List<EmpleadoEnProyecto> buscarPorProyecto(Proyecto proyecto) {
		return empleadoEnProyectoRespository.findAllByProyecto(proyecto);
	}


	@Override
	public EmpleadoEnProyecto asignarEmpleado(EmpleadoEnProyecto nuevoEmpleadoAsignado) {
		  return empleadoEnProyectoRespository.save(nuevoEmpleadoAsignado);
		
	}

}
