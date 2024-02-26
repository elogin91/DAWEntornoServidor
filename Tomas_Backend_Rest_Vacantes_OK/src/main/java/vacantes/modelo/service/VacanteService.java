package vacantes.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacantes.modelo.dto.VacanteDto;
import vacantes.modelo.entidades.EstatusVacante;
import vacantes.modelo.entidades.Vacante;
import vacantes.modelo.repository.VacanteRepository;

@Service
public class VacanteService {
	@Autowired
	private VacanteRepository vacanteRepository;
	@Autowired
	private CategoriaService categoriaService;

	public List<Vacante> mostrarTodasVacantes() {
		return vacanteRepository.findAll();
	}
	
	public Vacante buscarUnaVacante(int id) {
		return vacanteRepository.findById(id).orElse(null);
	}
	
	public Vacante altaVacante(Vacante vacante) {
		return vacanteRepository.save(vacante);
	}
	
	public Vacante handlerVacanteRequest(VacanteDto vacanteRequest) {
		Vacante vacanteMap = new Vacante();
		vacanteMap.setNombre(vacanteRequest.getNombre());
		vacanteMap.setDescripcion(vacanteRequest.getDescripcion());
		vacanteMap.setDestacado(vacanteRequest.getDestacado());
		vacanteMap.setDetalles(vacanteRequest.getDetalles());
		vacanteMap.setEstatus(EstatusVacante.CREADA);
		vacanteMap.setFecha(vacanteRequest.getFecha());
		vacanteMap.setImagen(vacanteRequest.getImagen());
		vacanteMap.setSalario(vacanteRequest.getSalario());
		vacanteMap.setCategoria(categoriaService.encontrarCategoria(vacanteRequest.getIdCategoria()));
		return vacanteMap;
	}
}
