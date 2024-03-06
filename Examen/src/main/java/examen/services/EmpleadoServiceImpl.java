package examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examen.entities.Empleado;
import examen.respositories.EmpleadoRespository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	EmpleadoRespository empleadoRepository;

	@Override
	public List<Empleado> mostrarEmpleadosAsignables(int idProyecto) {
		return empleadoRepository.empleadosAsignables(idProyecto);
	}

	@Override
	public Empleado buscarUno(int idEmpleado) {
		return empleadoRepository.findById(idEmpleado).orElse(null);
	}
	
}
