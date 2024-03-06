package examen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examen.entities.Proyecto;
import examen.respositories.ProyectoRespository;

@Service
public class ProyectoServiceImpl implements ProyectoService{
	@Autowired
	ProyectoRespository proyectoRepository;
	

	@Override
	public Proyecto buscarUno(int idProyecto) {
		return proyectoRepository.findById(idProyecto).orElse(null);
	}


	@Override
	public Proyecto modificarProyecto(Proyecto proyecto) {
		return proyectoRepository.save(proyecto);
	}

}
