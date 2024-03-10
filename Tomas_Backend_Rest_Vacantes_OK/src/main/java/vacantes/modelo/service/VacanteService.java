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
	
	public List<Vacante>buscarPorVacantesActivas(){
		return vacanteRepository.buscarVacantesActivas();
	}
	
	public List<Vacante>buscarPorVacantesActivasPorCategoria(int categoria){
		return vacanteRepository.buscarVacantesCategoria(categoria);
	}
	
	public Vacante altaVacante(VacanteDto vacanteRequest) {
		Vacante vacante=handlerVacanteRequest(vacanteRequest);
		return vacanteRepository.save(vacante);
	}
	public Vacante cancelarVacante(int idVacante) {
		Vacante vacante = buscarUnaVacante(idVacante);
		vacante.setEstatus(EstatusVacante.CANCELADA);
		return vacanteRepository.save(vacante);
	}
	
	public Vacante adjudicarVacante(int idVacante) {
		Vacante vacante = buscarUnaVacante(idVacante);
		vacante.setEstatus(EstatusVacante.CUBIERTA);
		return vacanteRepository.save(vacante);
	}
	
	private Vacante handlerVacanteRequest(VacanteDto vacanteRequest) {
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

	public Vacante modificarVacante(VacanteDto vacanteDto) {
		Vacante vacante = buscarUnaVacante(vacanteDto.getIdVacante());
		vacante.setNombre(vacanteDto.getNombre());
		vacante.setDescripcion(vacanteDto.getDescripcion());
		vacante.setDestacado(vacanteDto.getDestacado());
		vacante.setDetalles(vacanteDto.getDetalles());
		vacante.setImagen(vacanteDto.getImagen());
		vacante.setSalario(vacanteDto.getSalario());
		vacante.setCategoria(categoriaService.encontrarCategoria(vacanteDto.getIdCategoria()));
		
		return vacanteRepository.save(vacante);
	}
}
