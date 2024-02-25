package vacantes.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacantes.modelo.entidades.Vacante;
import vacantes.modelo.repository.VacanteRepository;

@Service
public class VacanteService {
	@Autowired
	private VacanteRepository vacanteRepository;

	public List<Vacante> mostrarTodasVacantes() {
		return vacanteRepository.findAll();
	}
	
	public Vacante buscarUnaVacante(int id) {
		return vacanteRepository.findById(id).orElse(null);
	}
}
