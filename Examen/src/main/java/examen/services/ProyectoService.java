package examen.services;

import examen.entities.Proyecto;

public interface ProyectoService {
	Proyecto buscarUno(int idProyecto);

	Proyecto modificarProyecto(Proyecto proyecto);
}
